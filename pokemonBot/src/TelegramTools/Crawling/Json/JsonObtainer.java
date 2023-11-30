package TelegramTools.Crawling.Json;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonObtainer {


    public static void visualizeJson(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            // Se il nodo è un oggetto, scorro attraverso i suoi campi
            jsonNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();

                // Se il campo è "pokemon", verifico se il valore è un array
                if ("pokemon".equals(fieldName) && fieldValue.isArray()) {
                    for (JsonNode pokemon : fieldValue) {
                        // Stampo ogni elemento Pokemon
                        System.out.println(pokemon.get("name"));
                    }
                } else {
                    // Se il campo non è "pokemon", continuo la ricerca ricorsiva
                    visualizeJson(fieldValue);
                }
            });
        } else if (jsonNode.isArray()) {
            // Se il nodo è un array, scorro attraverso i suoi elementi
            jsonNode.forEach(JsonObtainer::visualizeJson);
        }
    }
}
