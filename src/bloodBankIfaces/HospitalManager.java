package bloodBankIfaces;

import bloodBankPOJOs.Hospital;
import bloodBankPOJOs.Stock;

public interface HospitalManager {
	public void registerUserHospital(Hospital userToRegister);

	public void deleteUserHospital(Hospital userToDelete);

	public void extractionForHospital(Stock stockValid, String typeOfBlood);// funcion que compruebe que hay
	// stock y que la sangre no tenga una date caducada y extraer del stock
	// VERIFICAR QUE EL HOSPITAL MANAGER SE HA REGUSTRADO

}
