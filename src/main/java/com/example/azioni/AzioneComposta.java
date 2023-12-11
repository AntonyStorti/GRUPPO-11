package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;

import java.io.Serializable;


public class AzioneComposta implements Azione, Serializable {


    private Azione primaAzione;
    private Azione secondaAzione;
    private Azione terzaAzione;


    public AzioneComposta(Azione primaAzione, Azione secondaAzione, Azione terzaAzione) {
        this.primaAzione = primaAzione;
        this.secondaAzione = secondaAzione;
        this.terzaAzione = terzaAzione;
    }

    public AzioneComposta(Azione primaAzione) {
        this.primaAzione = primaAzione;
    }

    public AzioneComposta(Azione primaAzione, Azione secondaAzione) {
        this.primaAzione = primaAzione;
        this.secondaAzione = secondaAzione;
    }



    @Override
    public void eseguiAzione() {
        if (primaAzione != null)
            primaAzione.eseguiAzione();
        if (secondaAzione != null)
            secondaAzione.eseguiAzione();
        if (terzaAzione != null)
            terzaAzione.eseguiAzione();

    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        if (primaAzione != null && secondaAzione != null && terzaAzione != null) {
            jsonObject.put("numeroAzioni", 3);
            jsonObject.put("primaAzione", primaAzione.toJSONObject());
            jsonObject.put("secondaAzione", secondaAzione.toJSONObject());
            jsonObject.put("terzaAzione", terzaAzione.toJSONObject());
        }
        else if (primaAzione != null && secondaAzione != null) {
            jsonObject.put("numeroAzioni", 2);
            jsonObject.put("primaAzione" ,primaAzione.toJSONObject());
            jsonObject.put("secondaAzione", secondaAzione.toJSONObject());
        }
        else {
            jsonObject.put("numeroAzioni", 1);
            jsonObject.put("primaAzione", primaAzione.toJSONObject());
        }
        return jsonObject;
    }

    public static Azione deserialize(JSONObject jsonAzione) {
        int numAzioni = jsonAzione.getInt("numeroAzioni");
        if (numAzioni == 3) {
            JSONObject obj1 = jsonAzione.getJSONObject("primaAzione");
            String tipoAzione1 = obj1.getString("tipo");
            Azione a1 = null;
            if ("FinestraDialogo".equals(tipoAzione1)) {
                a1 = FinestraDialogo.deserialize(jsonAzione);
            }
            if ("RiproduciAudio".equals(tipoAzione1)) {
                a1 = RiproduciAudio.deserialize(jsonAzione);
            }
            if ("AggiungiStringa".equals(tipoAzione1)) {
                a1 = AggiungiStringa.deserialize(jsonAzione);
            }
            if ("CopiaSposta".equals(tipoAzione1)) {
                a1 = CopiaSposta.deserialize(jsonAzione);
            }
            if ("EliminaFile".equals(tipoAzione1)) {
                a1 = EliminaFile.deserialize(jsonAzione);
            }
            if ("EseguiProgramma".equals(tipoAzione1)) {
                a1 = EseguiProgramma.deserialize(jsonAzione);
            }
            if("CambiaValoreContatore".equals(tipoAzione1)) {
                a1 = CambiaValoreContatore.deserialize(jsonAzione);
            }
            if("SommaValoreContatore".equals(tipoAzione1)) {
                a1 = SommaValoreContatore.deserialize(jsonAzione);
            }
            if("SommaContatori".equals(tipoAzione1)) {
                a1 = SommaContatori.deserialize(jsonAzione);
            }
            JSONObject obj2 = jsonAzione.getJSONObject("secondaAzione");
            String tipoAzione2 = obj2.getString("tipo");
            Azione a2 = null;
            if ("FinestraDialogo".equals(tipoAzione2)) {
                a2 = FinestraDialogo.deserialize(jsonAzione);
            }
            if ("RiproduciAudio".equals(tipoAzione2)) {
                a2 = RiproduciAudio.deserialize(jsonAzione);
            }
            if ("AggiungiStringa".equals(tipoAzione2)) {
                a2 = AggiungiStringa.deserialize(jsonAzione);
            }
            if ("CopiaSposta".equals(tipoAzione2)) {
                a2 = CopiaSposta.deserialize(jsonAzione);
            }
            if ("EliminaFile".equals(tipoAzione2)) {
                a2 = EliminaFile.deserialize(jsonAzione);
            }
            if ("EseguiProgramma".equals(tipoAzione2)) {
                a2 = EseguiProgramma.deserialize(jsonAzione);
            }
            if("CambiaValoreContatore".equals(tipoAzione2)) {
                a2 = CambiaValoreContatore.deserialize(jsonAzione);
            }
            if("SommaValoreContatore".equals(tipoAzione2)) {
                a2 = SommaValoreContatore.deserialize(jsonAzione);
            }
            if("SommaContatori".equals(tipoAzione2)) {
                a2 = SommaContatori.deserialize(jsonAzione);
            }
            JSONObject obj3 = jsonAzione.getJSONObject("terzaAzione");
            String tipoAzione3 = obj3.getString("tipo");
            Azione a3 = null;
            if ("FinestraDialogo".equals(tipoAzione3)) {
                a3 = FinestraDialogo.deserialize(jsonAzione);
            }
            if ("RiproduciAudio".equals(tipoAzione3)) {
                a3 = RiproduciAudio.deserialize(jsonAzione);
            }
            if ("AggiungiStringa".equals(tipoAzione3)) {
                a3 = AggiungiStringa.deserialize(jsonAzione);
            }
            if ("CopiaSposta".equals(tipoAzione3)) {
                a3 = CopiaSposta.deserialize(jsonAzione);
            }
            if ("EliminaFile".equals(tipoAzione3)) {
                a3 = EliminaFile.deserialize(jsonAzione);
            }
            if ("EseguiProgramma".equals(tipoAzione3)) {
                a3 = EseguiProgramma.deserialize(jsonAzione);
            }
            if("CambiaValoreContatore".equals(tipoAzione3)) {
                a3 = CambiaValoreContatore.deserialize(jsonAzione);
            }
            if("SommaValoreContatore".equals(tipoAzione3)) {
                a3 = SommaValoreContatore.deserialize(jsonAzione);
            }
            if("SommaContatori".equals(tipoAzione3)) {
                a3 = SommaContatori.deserialize(jsonAzione);
            }

            return new AzioneComposta(a1, a2, a3);
        }
        if (numAzioni == 2) {
            JSONObject obj1 = jsonAzione.getJSONObject("primaAzione");
            String tipoAzione1 = obj1.getString("tipo");
            Azione a1 = null;
            if ("FinestraDialogo".equals(tipoAzione1)) {
                a1 = FinestraDialogo.deserialize(jsonAzione);
            }
            if ("RiproduciAudio".equals(tipoAzione1)) {
                a1 = RiproduciAudio.deserialize(jsonAzione);
            }
            if ("AggiungiStringa".equals(tipoAzione1)) {
                a1 = AggiungiStringa.deserialize(jsonAzione);
            }
            if ("CopiaSposta".equals(tipoAzione1)) {
                a1 = CopiaSposta.deserialize(jsonAzione);
            }
            if ("EliminaFile".equals(tipoAzione1)) {
                a1 = EliminaFile.deserialize(jsonAzione);
            }
            if ("EseguiProgramma".equals(tipoAzione1)) {
                a1 = EseguiProgramma.deserialize(jsonAzione);
            }
            if ("CambiaValoreContatore".equals(tipoAzione1)) {
                a1 = CambiaValoreContatore.deserialize(jsonAzione);
            }
            if ("SommaValoreContatore".equals(tipoAzione1)) {
                a1 = SommaValoreContatore.deserialize(jsonAzione);
            }
            if ("SommaContatori".equals(tipoAzione1)) {
                a1 = SommaContatori.deserialize(jsonAzione);
            }
            JSONObject obj2 = jsonAzione.getJSONObject("secondaAzione");
            String tipoAzione2 = obj2.getString("tipo");
            Azione a2 = null;
            if ("FinestraDialogo".equals(tipoAzione2)) {
                a2 = FinestraDialogo.deserialize(jsonAzione);
            }
            if ("RiproduciAudio".equals(tipoAzione2)) {
                a2 = RiproduciAudio.deserialize(jsonAzione);
            }
            if ("AggiungiStringa".equals(tipoAzione2)) {
                a2 = AggiungiStringa.deserialize(jsonAzione);
            }
            if ("CopiaSposta".equals(tipoAzione2)) {
                a2 = CopiaSposta.deserialize(jsonAzione);
            }
            if ("EliminaFile".equals(tipoAzione2)) {
                a2 = EliminaFile.deserialize(jsonAzione);
            }
            if ("EseguiProgramma".equals(tipoAzione2)) {
                a2 = EseguiProgramma.deserialize(jsonAzione);
            }
            if ("CambiaValoreContatore".equals(tipoAzione2)) {
                a2 = CambiaValoreContatore.deserialize(jsonAzione);
            }
            if ("SommaValoreContatore".equals(tipoAzione2)) {
                a2 = SommaValoreContatore.deserialize(jsonAzione);
            }
            if ("SommaContatori".equals(tipoAzione2)) {
                a2 = SommaContatori.deserialize(jsonAzione);
            }

            return new AzioneComposta(a1, a2);
        }
        JSONObject obj1 = jsonAzione.getJSONObject("primaAzione");
        String tipoAzione1 = obj1.getString("tipo");
        Azione a1 = null;
        if ("FinestraDialogo".equals(tipoAzione1)) {
            a1 = FinestraDialogo.deserialize(jsonAzione);
        }
        if ("RiproduciAudio".equals(tipoAzione1)) {
            a1 = RiproduciAudio.deserialize(jsonAzione);
        }
        if ("AggiungiStringa".equals(tipoAzione1)) {
            a1 = AggiungiStringa.deserialize(jsonAzione);
        }
        if ("CopiaSposta".equals(tipoAzione1)) {
            a1 = CopiaSposta.deserialize(jsonAzione);
        }
        if ("EliminaFile".equals(tipoAzione1)) {
            a1 = EliminaFile.deserialize(jsonAzione);
        }
        if ("EseguiProgramma".equals(tipoAzione1)) {
            a1 = EseguiProgramma.deserialize(jsonAzione);
        }
        if ("CambiaValoreContatore".equals(tipoAzione1)) {
            a1 = CambiaValoreContatore.deserialize(jsonAzione);
        }
        if ("SommaValoreContatore".equals(tipoAzione1)) {
            a1 = SommaValoreContatore.deserialize(jsonAzione);
        }
        if ("SommaContatori".equals(tipoAzione1)) {
            a1 = SommaContatori.deserialize(jsonAzione);
        }

        return new AzioneComposta(a1);

    }


    @Override
    public String toString() {
        return "AzioneComposta: " + primaAzione + " & " + secondaAzione + " & " + terzaAzione;
    }

    public String getTipo() {
        return "AzioneComposta";
    }

}
