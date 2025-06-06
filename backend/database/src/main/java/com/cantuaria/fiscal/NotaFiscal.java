package com.cantuaria.fiscal;

import com.cantuaria.fiscal.nfe.Det;
import com.cantuaria.fiscal.nfe.Emit;
import com.cantuaria.fiscal.nfe.IDE;
import com.cantuaria.fiscal.nfe.InformacaoAdicional;
import com.cantuaria.fiscal.nfe.Total;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NFO_NOTA_FISCAL_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaFiscal {

    public static final String ID = "NFO_ID";

    @Id
    @Column(name = ID)
    private String id;

    @Column(name = "NFO_DS_PATH")
    private String caminhoArquivo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private IDE ide;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Emit emit;

    @Column(name = "NFO_DS_AUT_CNPJ")
    private String autXmlCnpj;

    @Builder.Default
    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private List<Det> dets = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Total total;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private InformacaoAdicional informacaoAdicional;

}