package io.github.mazuh.terminal588;

import android.util.Log;

/**
 * @author mazuh
 */
public class Sentido {

    /* Dados do veículos deste sentido */
    private short cod;
    private Onibus[] onibusQuePartem;

    /* Identificação dos sentidos e seus horários */
    public static final short DIRETO  = 1;
    public static final short INVERSO = 2;


    /**
     * Construtor.
     *
     * @param sentido a identificação do sentido (usar as constantes!)
     */
    public Sentido(short sentido) {
        this.cod = sentido;

        this.onibusQuePartem = getOnibusQuePartem(); // init
    }


    /**
     * Encontra as instâncias do próximo e do mais recente ônibus a partirem
     *
     * @return array de instâncias de Ônibus, cujas posições:
     *         0 = último que já partiu (null se nenhum partiu ainda),
     *         1 = próximo a partir (null se não houver um próximo nesta data)
     **/
    public Onibus[] findOnibusAnteriorEProximo(){
        Onibus proximo  = null;
        Onibus anterior = null;

        for (Onibus onibus : this.onibusQuePartem) {
            if (!onibus.jaPartiu()){
                proximo = onibus;
                break;
            } else{
                anterior = onibus;
            }
        }

        return new Onibus[] {anterior, proximo};
    }


    /**
     * Deve ser chamado no Construtor!!! Constrói vetor de ônibus deste sentido.
     *
     * @return um vetor com os objetos de Onibus que partem neste sentido; vetor vazio caso o
     *         construtor de Sentido tenha sido inicializado com parâmetro errado.
     **/
    private Onibus[] getOnibusQuePartem(){
        Onibus[] onibus = new Onibus[]{};

        switch (this.cod){
            case Sentido.DIRETO:
                onibus = new Onibus[]{
                        new Onibus(Onibus.GUANABARA, "06:00"),
                        new Onibus(Onibus.STMARIA,   "06:10"),
                        new Onibus(Onibus.REUNIDAS,  "06:30"),
                        new Onibus(Onibus.GUANABARA, "06:35"),
                        new Onibus(Onibus.GUANABARA, "06:40"),
                        new Onibus(Onibus.STMARIA,   "06:46"),

                        new Onibus(Onibus.CIDNATAL,  "07:00"),
                        new Onibus(Onibus.REUNIDAS,  "07:05"),
                        new Onibus(Onibus.GUANABARA, "07:10"),
                        new Onibus(Onibus.GUANABARA, "07:18"),
                        new Onibus(Onibus.STMARIA,   "07:22"),
                        new Onibus(Onibus.CIDNATAL,  "07:35"),
                        new Onibus(Onibus.REUNIDAS,  "07:40"),
                        new Onibus(Onibus.GUANABARA, "07:45"),

                        new Onibus(Onibus.STMARIA,   "08:00"),
                        new Onibus(Onibus.CIDNATAL,  "08:27"),
                        new Onibus(Onibus.REUNIDAS,  "08:35"),
                        new Onibus(Onibus.GUANABARA, "08:45"),
                        new Onibus(Onibus.GUANABARA, "08:55"),

                        new Onibus(Onibus.CIDNATAL,  "09:15"),
                        new Onibus(Onibus.GUANABARA, "09:35"),
                        new Onibus(Onibus.STMARIA,   "09:45"),

                        new Onibus(Onibus.REUNIDAS,  "10:05"),
                        new Onibus(Onibus.GUANABARA, "10:15"),
                        new Onibus(Onibus.GUANABARA, "10:20"),
                        new Onibus(Onibus.STMARIA,   "10:25"),
                        new Onibus(Onibus.REUNIDAS,  "10:35"),
                        new Onibus(Onibus.GUANABARA, "10:55"),

                        new Onibus(Onibus.CIDNATAL,  "11:00"),
                        new Onibus(Onibus.GUANABARA, "11:04"),
                        new Onibus(Onibus.STMARIA,   "11:08"),
                        new Onibus(Onibus.REUNIDAS,  "11:16"),
                        new Onibus(Onibus.GUANABARA, "11:30"),
                        new Onibus(Onibus.GUANABARA, "11:38"),
                        new Onibus(Onibus.CIDNATAL,  "11:35"),
                        new Onibus(Onibus.STMARIA,   "11:43"),
                        new Onibus(Onibus.REUNIDAS,  "11:51"),

                        new Onibus(Onibus.GUANABARA, "12:05"),
                        new Onibus(Onibus.CIDNATAL,  "12:10"),
                        new Onibus(Onibus.GUANABARA, "12:15"),
                        new Onibus(Onibus.STMARIA,   "12:20"),
                        new Onibus(Onibus.REUNIDAS,  "12:30"),
                        new Onibus(Onibus.GUANABARA, "12:45"),
                        new Onibus(Onibus.CIDNATAL,  "12:52"),
                        new Onibus(Onibus.GUANABARA, "12:59"),

                        new Onibus(Onibus.STMARIA,   "13:06"),
                        new Onibus(Onibus.REUNIDAS,  "13:19"),
                        new Onibus(Onibus.GUANABARA, "13:40"),
                        new Onibus(Onibus.CIDNATAL,  "13:47"),
                        new Onibus(Onibus.GUANABARA, "13:54"),

                        new Onibus(Onibus.STMARIA,   "14:01"),
                        new Onibus(Onibus.REUNIDAS,  "14:15"),
                        new Onibus(Onibus.GUANABARA, "14:36"),
                        new Onibus(Onibus.CIDNATAL,  "14:43"),
                        new Onibus(Onibus.GUANABARA, "14:50"),
                        new Onibus(Onibus.STMARIA,   "14:57"),

                        new Onibus(Onibus.REUNIDAS,  "15:11"),
                        new Onibus(Onibus.GUANABARA, "15:32"),
                        new Onibus(Onibus.CIDNATAL,  "15:39"),
                        new Onibus(Onibus.GUANABARA, "15:46"),
                        new Onibus(Onibus.STMARIA,   "15:53"),

                        new Onibus(Onibus.REUNIDAS,  "16:06"),
                        new Onibus(Onibus.CIDNATAL,  "16:24"),
                        new Onibus(Onibus.GUANABARA, "16:30"),
                        new Onibus(Onibus.REUNIDAS,  "16:42"),

                        new Onibus(Onibus.CIDNATAL,  "17:00"),
                        new Onibus(Onibus.GUANABARA, "17:05"),
                        new Onibus(Onibus.GUANABARA, "17:10"),
                        new Onibus(Onibus.REUNIDAS,  "17:20"),
                        new Onibus(Onibus.STMARIA,   "17:30"),
                        new Onibus(Onibus.CIDNATAL,  "17:40"),
                        new Onibus(Onibus.GUANABARA, "17:45"),
                        new Onibus(Onibus.GUANABARA, "17:50"),
                        new Onibus(Onibus.REUNIDAS,  "17:55"),

                        new Onibus(Onibus.STMARIA,   "18:07"),
                        new Onibus(Onibus.CIDNATAL,  "18:21"),
                        new Onibus(Onibus.GUANABARA, "18:29"),
                        new Onibus(Onibus.GUANABARA, "18:36"),
                        new Onibus(Onibus.STMARIA,   "18:43"),
                        new Onibus(Onibus.CIDNATAL,  "18:58"),

                        new Onibus(Onibus.GUANABARA, "19:06"),
                        new Onibus(Onibus.STMARIA,   "19:22"),
                        new Onibus(Onibus.REUNIDAS,  "19:30"),
                        new Onibus(Onibus.GUANABARA, "19:50"),

                        new Onibus(Onibus.STMARIA,   "20:08"),
                        new Onibus(Onibus.GUANABARA, "20:24"),
                        new Onibus(Onibus.REUNIDAS,  "20:16"),

                        new Onibus(Onibus.CIDNATAL,  "21:00"),
                        new Onibus(Onibus.STMARIA,   "21:20"),
                        new Onibus(Onibus.REUNIDAS,  "21:30"),
                        new Onibus(Onibus.GUANABARA, "21:40"),

                        new Onibus(Onibus.CIDNATAL,  "22:05"),
                        new Onibus(Onibus.GUANABARA, "22:15"),
                        new Onibus(Onibus.CIDNATAL,  "22:40"),
                        new Onibus(Onibus.GUANABARA, "22:50"),
                };
                break;

            case Sentido.INVERSO:
                onibus = new Onibus[]{
                        // TODO: manualmente ordenar crescentemente por parâmetro de horário
                        // # 3
                        new Onibus(Onibus.GUANABARA, "06:20"),
                        new Onibus(Onibus.GUANABARA, "06:55"),
                        new Onibus(Onibus.GUANABARA, "07:30"),
                        new Onibus(Onibus.GUANABARA, "08:18"),
                        new Onibus(Onibus.GUANABARA, "09:55"),
                        new Onibus(Onibus.GUANABARA, "10:30"),
                        new Onibus(Onibus.GUANABARA, "11:12"),
                        new Onibus(Onibus.GUANABARA, "11:47"),
                        new Onibus(Onibus.GUANABARA, "12:25"),
                        new Onibus(Onibus.GUANABARA, "13:12"),
                        new Onibus(Onibus.GUANABARA, "14:08"),
                        new Onibus(Onibus.GUANABARA, "15:04"),
                        new Onibus(Onibus.GUANABARA, "16:00"),
                        new Onibus(Onibus.GUANABARA, "16:36"),
                        new Onibus(Onibus.GUANABARA, "17:15"),
                        new Onibus(Onibus.GUANABARA, "19:14"),
                        new Onibus(Onibus.GUANABARA, "20:00"),
                        new Onibus(Onibus.GUANABARA, "21:20"),
                        // # 6
                        new Onibus(Onibus.CONCEICAO, "06:42"),
                        new Onibus(Onibus.CONCEICAO, "07:14"),
                        new Onibus(Onibus.CONCEICAO, "07:52"),
                        new Onibus(Onibus.CONCEICAO, "09:25"),
                        new Onibus(Onibus.CONCEICAO, "10:10"),
                        new Onibus(Onibus.CONCEICAO, "10:45"),
                        new Onibus(Onibus.CONCEICAO, "11:20"),
                        new Onibus(Onibus.CONCEICAO, "11:55"),
                        new Onibus(Onibus.CONCEICAO, "12:35"),
                        new Onibus(Onibus.CONCEICAO, "13:26"),
                        new Onibus(Onibus.CONCEICAO, "14:22"),
                        new Onibus(Onibus.CONCEICAO, "15:18"),
                        new Onibus(Onibus.CONCEICAO, "16:12"),
                        new Onibus(Onibus.CONCEICAO, "16:48"),
                        new Onibus(Onibus.CONCEICAO, "17:25"),
                        new Onibus(Onibus.CONCEICAO, "18:00"),
                        new Onibus(Onibus.CONCEICAO, "19:40"),
                        new Onibus(Onibus.CONCEICAO, "20:32"),
                        // # 7
                        new Onibus(Onibus.VIASUL, "06:50"),
                        new Onibus(Onibus.VIASUL, "07:26"),
                        new Onibus(Onibus.VIASUL, "08:09"),
                        new Onibus(Onibus.VIASUL, "09:05"),
                        new Onibus(Onibus.VIASUL, "10:50"),
                        new Onibus(Onibus.VIASUL, "11:25"),
                        new Onibus(Onibus.VIASUL, "12:00"),
                        new Onibus(Onibus.VIASUL, "12:40"),
                        new Onibus(Onibus.VIASUL, "13:33"),
                        new Onibus(Onibus.VIASUL, "14:29"),
                        new Onibus(Onibus.VIASUL, "15:25"),
                        new Onibus(Onibus.VIASUL, "16:18"),
                        new Onibus(Onibus.VIASUL, "16:53"),
                        new Onibus(Onibus.VIASUL, "17:35"),
                        new Onibus(Onibus.VIASUL, "18:14"),
                        new Onibus(Onibus.VIASUL, "18:50"),
                        new Onibus(Onibus.VIASUL, "20:40"),
                        new Onibus(Onibus.VIASUL, "21:50"),
                        new Onibus(Onibus.VIASUL, "22:30"),
                };
                break;
        }

        return onibus;
    }




    /* ACESSOS PADRÃO */

    public short get() {
        return cod;
    }
}
