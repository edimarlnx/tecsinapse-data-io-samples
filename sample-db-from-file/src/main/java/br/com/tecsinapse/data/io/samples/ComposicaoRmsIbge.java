package br.com.tecsinapse.data.io.samples;


import br.com.tecsinapse.exporter.annotation.TableCellMapping;
import br.com.tecsinapse.exporter.converter.IntegerTableCellConverter;
import br.com.tecsinapse.exporter.converter.LocalDateTableCellConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "composicao_rms_ibge")
public class ComposicaoRmsIbge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "composicao_rms_ibge_id")
    private Integer id;

    @Column(name = "regiao", length = 100)
    private String regiao;

    @Column(name = "codigo_municipio")
    private Integer codigoMunicipio;

    @Column(name = "nome_municipio", length = 100)
    private String nomeMunicipio;

    @Column(name = "data_lei", columnDefinition = "timestamp")
    private LocalDate dataLei;

    public void setId(Integer id) {
        this.id = id;
    }

    @TableCellMapping(columnIndex = 0)
    public void setRegiao(String regiao) {
        this.regiao = regiao != null && regiao.length() > 100 ? regiao.substring(0,100) : regiao;
    }

    @TableCellMapping(columnIndex = 2, converter = IntegerTableCellConverter.class)
    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    @TableCellMapping(columnIndex = 3)
    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    @TableCellMapping(columnIndex = 5, converter = LocalDateTableCellConverter.class)
    public void setDataLei(LocalDate dataLei) {
        this.dataLei = dataLei;
    }

    public Integer getId() {
        return id;
    }

    public String getRegiao() {
        return regiao;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public LocalDate getDataLei() {
        return dataLei;
    }
}
