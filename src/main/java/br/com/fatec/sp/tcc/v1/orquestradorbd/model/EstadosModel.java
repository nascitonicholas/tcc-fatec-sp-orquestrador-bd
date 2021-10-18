package br.com.fatec.sp.tcc.v1.orquestradorbd.model;

import br.com.fatec.sp.tcc.v1.orquestradorbd.enums.TipoEnderecoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "estado")
public class EstadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;
    @Column(name = "nome")
    private String nome;
}
