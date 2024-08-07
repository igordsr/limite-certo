package com.limite_certo.dto;

public class PagamentoResponse {
    private String chave_pagamento;

    public PagamentoResponse(Long id) {
        this.chave_pagamento = id.toString();
    }

    public String getChave_pagamento() {
        return chave_pagamento;
    }

    public void setChave_pagamento(String chave_pagamento) {
        this.chave_pagamento = chave_pagamento;
    }
}