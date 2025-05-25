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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NFO_NOTA_FISCAL_ORIGINAL")
public class NotaFiscal {

    public static final String ID = "NFO_ID";

    @Id
    @Column(name = ID)
    private String id;

    @Column(name = "NFO_DS_PATH")
    private String caminhoArquivo;

    @OneToOne(mappedBy = "notaFiscal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private IDE ide;

    @OneToOne(mappedBy = "notaFiscal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Emit emit;

    @Column(name = "NFO_DS_AUT_CNPJ")
    private String autXmlCnpj;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private List<Det> dets = new ArrayList<>();

    @OneToOne(mappedBy = "notaFiscal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Total total;

    @OneToOne(mappedBy = "notaFiscal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private InformacaoAdicional informacaoAdicional;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public IDE getIde() {
        return ide;
    }

    public void setIde(IDE ide) {
        this.ide = ide;
    }

    public Emit getEmit() {
        return emit;
    }

    public void setEmit(Emit emit) {
        this.emit = emit;
    }

    public String getAutXmlCnpj() {
        return autXmlCnpj;
    }

    public void setAutXmlCnpj(String autXmlCnpj) {
        this.autXmlCnpj = autXmlCnpj;
    }

    public List<Det> getDets() {
        return dets;
    }

    public void setDets(List<Det> dets) {
        this.dets = dets;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public InformacaoAdicional getInformacaoAdicional() {
        return informacaoAdicional;
    }

    public void setInformacaoAdicional(InformacaoAdicional informacaoAdicional) {
        this.informacaoAdicional = informacaoAdicional;
    }
}