import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://13.238.167.130/weather"))
                .header("Accept", "text/event-stream")
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body)
                .thenAccept(Client::consumeEventStream)
                .join();
    }

    private static void consumeEventStream(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(Client::explainLine)
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Error reading Server-Sent Events (SSE): " + e.getMessage());
        }
    }

   
    static String explainLine(String line) {

        if (line.startsWith(":")) {
            return "Keep‑alive from server (SSE comment): \"" + line.substring(1).trim() + "\"";
        }

        if (line.startsWith("data:")) {
            String payload = line.substring(5).trim();
            String inner = trySummarisePayload(payload);
            return "SSE data event → " + inner;
        }

        if (line.contains("=") && !line.contains("{") && !line.contains("}")) {
            Map<String, String> pairs = new LinkedHashMap<>();
            for (String chunk : line.split("[,;\\s]+")) {
                int eq = chunk.indexOf('=');
                if (eq > 0 && eq < chunk.length() - 1) {
                    String k = chunk.substring(0, eq).trim();
                    String v = chunk.substring(eq + 1).trim();
                    if (!k.isEmpty()) pairs.put(k, v);
                }
            }
            if (!pairs.isEmpty()) {
                String details = pairs.entrySet().stream()
                        .map(e -> e.getKey() + " = " + e.getValue())
                        .collect(Collectors.joining(", "));
                return "Key/Value reading → " + details;
            }
        }

        if (line.contains(",") && !line.contains("{")) {
            String[] parts = Arrays.stream(line.split(","))
                    .map(String::trim)
                    .toArray(String[]::new);
            String joined = "";
            for (int i = 0; i < parts.length; i++) {
                joined += String.format("[field %d] %s", i, parts[i]);
                if (i < parts.length - 1) joined += "; ";
            }
            return "CSV reading → " + joined;
        }

        if (looksLikeJson(line)) {
            return "JSON reading → " + describeJsonShallow(line);
        }

        return "Unrecognised format, raw line: \"" + line + "\"";
    }


    private static String trySummarisePayload(String payload) {
        if (looksLikeJson(payload)) {
            return "JSON payload → " + describeJsonShallow(payload);
        } else if (payload.contains("=")) {
            // Reuse the key=value logic by delegating
            return explainLine(payload).replace("Key/Value reading → ", "");
        } else if (payload.contains(",")) {
            return explainLine(payload).replace("CSV reading → ", "");
        }
        return "text payload → \"" + payload + "\"";
    }

    private static boolean looksLikeJson(String s) {
        String t = s.trim();
        return (t.startsWith("{") && t.endsWith("}")) || (t.startsWith("[") && t.endsWith("]"));
    }


    private static String describeJsonShallow(String json) {
        String t = json.trim();
        try {
            if (t.startsWith("{") && t.endsWith("}")) {
                // naive top-level key extraction (no full JSON parsing to keep it stdlib-only)
                String inner = t.substring(1, t.length() - 1).trim();
                if (inner.isEmpty()) return "empty object {}";
                // split on commas not inside quotes (best-effort)
                String[] pieces = inner.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String keys = Arrays.stream(pieces)
                        .map(String::trim)
                        .map(p -> p.split(":(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", 2)[0])
                        .map(k -> k.replaceAll("^\\s*\"|\"\\s*$", ""))
                        .collect(Collectors.joining(", "));
                return "object with keys: " + keys;
            }
            if (t.startsWith("[") && t.endsWith("]")) {
                // estimate element count by splitting top-level commas (best-effort)
                String inner = t.substring(1, t.length() - 1).trim();
                if (inner.isEmpty()) return "empty array []";
                String[] pieces = inner.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                return "array with ~" + pieces.length + " top‑level element(s)";
            }
        } catch (Exception ignored) {
        }
        return "JSON payload";
    }
}
