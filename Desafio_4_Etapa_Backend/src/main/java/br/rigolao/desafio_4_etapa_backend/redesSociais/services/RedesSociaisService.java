package br.rigolao.desafio_4_etapa_backend.redesSociais.services;

import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;

public interface RedesSociaisService {

    void saveRedeSocial(RedesSociaisModel redesSociaisModel);

    void deleteRedeSocial(RedesSociaisModel redesSociaisModel);

}
