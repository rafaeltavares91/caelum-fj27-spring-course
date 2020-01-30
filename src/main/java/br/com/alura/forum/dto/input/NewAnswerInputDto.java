package br.com.alura.forum.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewAnswerInputDto {

	@NotBlank
    @Size(min = 5)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
