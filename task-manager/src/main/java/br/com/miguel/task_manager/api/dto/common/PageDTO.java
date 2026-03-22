package br.com.miguel.task_manager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDTO<T> {
    private List<T> registers;              // lista de registros da página
    private int page;                       // número da página atual
    private int size;                       // tamanho da página
    private Long totalElements;             // total de registros
    private int totalPages;                 // total de páginas
}
