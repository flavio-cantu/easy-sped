package com.cantuaria.item;

import com.cantuaria.validation.EnumSped;

public enum ItemType implements EnumSped<String> {

    MERCADORIA_REVENDA("00", "Mercadoria para Revenda"),
    MATERIA_PRIMA("01", "Matéria-Prima"),
    EMBALAGEM("02", "Embalagem"),
    PRODUTO_EM_PROCESSO("03", "Produto em Processo"),
    PRODUTO_ACABADO("04", "Produto Acabado"),
    SUBPRODUTO("05", "Subproduto"),
    PRODUTO_INTERMEDIARIO("06", "Produto Intermediário"),
    MATERIAL_USO_CONSUMO("07", "Material de Uso e Consumo"),
    ATIVO_IMOBILIZADO("08", "Ativo Imobilizado"),
    SERVICOS("09", "Serviços"),
    OUTROS_INSUMOS("10", "Outros insumos"),
    OUTROS("99", "Outros");

    private String code;
    private String description;

    ItemType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
