package a.c.execLivro;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class EstatisticaAutor {
    private final Map<String, Integer> autorContagem = new HashMap<>();

    public void registrarLivro(String autor) {
        autorContagem.put(autor, autorContagem.getOrDefault(autor, 0) + 1);
    }

    public Optional<String> autorMaisConsultado(){
        return autorContagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public Optional<String> autorMenosConsultado(){
        return autorContagem.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public Map<String, Integer> todasAsContagens() {
        return autorContagem;
    }
}

