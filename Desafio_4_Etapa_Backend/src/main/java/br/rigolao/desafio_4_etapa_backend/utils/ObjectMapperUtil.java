package br.rigolao.desafio_4_etapa_backend.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ObjectMapperUtil {

    private static final ModelMapper modelMapper;

    //Uma única instancia
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     *
     * @param <D> Tipo de resultado
     * @param <T> Tipo de entrada
     * @param entidadeEntrada Entidade a ser mapeada
     * @param classSaida Class do resultado
     * @return Retorna um novo objeto da classe passada
     */
    public static <D, T> D map(final T entidadeEntrada, Class<D> classSaida){
        return modelMapper.map(entidadeEntrada, classSaida);
    }

    /**
     *
     * @param <D> Tipo de resultado
     * @param <T> Tipo de entrada
     * @param listaEntrada Lista a ser mapeada
     * @param classSaida Class do resultado
     * @return Retorna uma lista mapeada para o n tipo class passado
     */
    public static <D, T> List<D> mapAll(final Collection<T> listaEntrada, Class<D> classSaida) {
        return listaEntrada.stream().map(
                entidade -> map(entidade, classSaida)).collect(Collectors.toList());
    }

}
