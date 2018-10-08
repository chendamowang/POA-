package benKeBiYeSheJi;

public class ShuiWeiKuRongQuXian {

	
	/**
	 * 狮子滩水库水位库容曲线
	 */
	public ShuiWeiKuRongQuXian() {
		
	}
	
	/**
	 * 由狮子滩当前库容确定相应水位
	 * @param dangQianKuRong
	 * @return
	 */
	public double get_DangQianShuiWei(double dangQianKuRong){
		dangQianKuRong = dangQianKuRong / 10000;
		double temp = Math.log(dangQianKuRong);
		double dangQianShuiWei = 9.7745 * temp + 235.12;
		return dangQianShuiWei;
	}
	
	/**
	 * 由狮子滩当前水位确定库容
	 * @param dangQianShuiWei 当前水位（m）
	 * @return 当前库容（m³）
	 */
	public double get_DangQianKuRong(double dangQianShuiWei){
		double temp = ((dangQianShuiWei - 235.12) / 9.7745);
		double dangQianKuRong = Math.exp(temp);
		dangQianKuRong = dangQianKuRong * 10000;
		return dangQianKuRong;
	}
	
//	//测试
//	public static void main(String[] args) {
//		ShuiWeiKuRongQuXian swkrqx = new ShuiWeiKuRongQuXian();
//		double dangQianKuRong = 871000000;
//		double dangQianShuiWei = swkrqx.get_DangQianShuiWei(dangQianKuRong);
//		System.out.println(dangQianShuiWei);
//		dangQianKuRong = swkrqx.get_DangQianKuRong(dangQianShuiWei);
//		System.out.println(dangQianKuRong);
//	}
	
	
}
