package io.github.mazuh.terminal588;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * @author mazuh
 */
public class Onibus {

    private Time horario;
    private String empresa;


    /**
     * Construtor.
     *
     * @param horario um objeto configurado com "hh:mm:00" da hora em que o busão parte,
     *                por exemplo horario = new Time(Time.valueOf("12:45:00")
     * @param empresa o nome da empresa que partirá em tal horário.
     */
    public Onibus(Time horario, String empresa) {
        this.horario = horario;
        this.empresa = empresa;
    }


    /**
     * Construtor.
     *
     * @return representação do ônibus no formato "hh:mm - empresa"
     */
    @Override
    public String toString() {
        return this.horario.toString().substring(0, 5) + " - " + this.empresa;
    }


    /**
     * Calcula quantos minutos faltam pra o ônibus partir.
     *
     * @return inteiro de minutos até o horário deste ônibus partir;
     *         -1 caso já tenha passado.
     */
    public int minutosParaPartir() {
        // configura Calendar tempo
        Calendar tempo = Calendar.getInstance();
        tempo.set(Calendar.MINUTE, 0);
        tempo.set(Calendar.MILLISECOND, 0);

        // gera ms de agora
        long agoraMs = tempo.getTimeInMillis();

        // configura tempo para partida e gera ms
        String[] partida = this.horario.toString().split(":");
        tempo.set(Calendar.HOUR_OF_DAY, Integer.valueOf(partida[0]));
        tempo.set(Calendar.MINUTE, Integer.valueOf(partida[1]));
        long partidaMs = tempo.getTimeInMillis();

        // calcula ms diferença e configura seu tempo
        long diferencaMs = partidaMs - agoraMs;
        if (diferencaMs < 0) return -1; // !
        tempo.setTimeInMillis(diferencaMs);

        // converte horas em ms antes de retornar
        return (tempo.get(Calendar.HOUR_OF_DAY) * 60) + tempo.get(Calendar.MINUTE);
    }


    /**
     * Saber se a previsão está disponível para hoje (exemplo: indisponível em fim de semana).
     * TODO: verificar feriados ou alguma outra anormalidade?
     *
     * @return false caso hoje seja fim de semana
     */
    public boolean hojeTemPrevisao(){
        // TODO
        return true;
    }



    /*
    * GETTERS PADRÃO
    */

    public Time getHorario() {
        return horario;
    }

    public String getEmpresa() {
        return empresa;
    }

}
