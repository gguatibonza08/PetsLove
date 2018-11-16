package co.com.petslove.petslovers.utilidades;

public enum EstablecimientosEnum {

    PASEADOR {
        @Override
        public String getNombre() {
            return "PASEADOR";

        }

        @Override
        public int getId() {

            return 1;
        }
    },
    VETERINARIA {
        @Override
        public String getNombre() {

            return "VETERINARIA";
        }

        @Override
        public int getId() {

            return 2;
        }

    },
    ALIMENTO {
        @Override
        public String getNombre() {
            return "ALIMENTO";
        }

        @Override
        public int getId() {

            return 3;
        }
    },
    ESTILISTA {
        @Override
        public String getNombre() {

            return "ESTILISTA";
        }

        @Override
        public int getId() {

            return 4;
        }
    },
    PERRO {
        @Override
        public String getNombre() {

            return "PERRO";
        }

        @Override
        public int getId() {

            return 5;
        }
    },

    GATO {
        @Override
        public String getNombre() {

            return "GATO";
        }

        @Override
        public int getId() {

            return 6;
        }
    },
    VACA {
        @Override
        public String getNombre() {

            return "VACA";
        }

        @Override
        public int getId() {

            return 7;
        }
    },
    CERDO {
        @Override
        public String getNombre() {

            return "CERDO";
        }

        @Override
        public int getId() {

            return 8;
        }
    },
    PEZ {
        @Override
        public String getNombre() {

            return "PEZ";
        }

        @Override
        public int getId() {

            return 9;
        }
    },
    AVE {
        @Override
        public String getNombre() {

            return "AVE";
        }

        @Override
        public int getId() {

            return 10;
        }
    },
    CABALLO {
        @Override
        public String getNombre() {

            return "CABALLO";
        }

        @Override
        public int getId() {

            return 11;
        }
    },
    OVEJA {
        @Override
        public String getNombre() {

            return "OVEJA";
        }

        @Override
        public int getId() {

            return 12;
        }
    };

    public abstract String getNombre();

    public abstract int getId();
}
