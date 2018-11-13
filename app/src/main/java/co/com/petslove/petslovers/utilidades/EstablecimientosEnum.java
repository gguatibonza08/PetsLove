package co.com.petslove.petslovers.utilidades;

public enum EstablecimientosEnum {
	
	PASEADOR{
		@Override
		public String getNombre() {
			return "PASEADOR";
			
		}
		@Override
		public int getId() {
			
			return 1;
		}
	},
	VETERINARIA{

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
	};

	public abstract String getNombre();
	public abstract int getId();
}
