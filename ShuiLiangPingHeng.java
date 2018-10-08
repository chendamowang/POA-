package benKeBiYeSheJi;

public class ShuiLiangPingHeng {
	
	/**
	 * 由水量平衡公式，得出时段末水库库容
	 * @param yueChuKuRong 初始库容（m³）
	 * @param yueRuKuJingLiuLiang 入库径流量（m³）
	 * @param yueChuKuJingLiuLiang 出库径流量（m³）
	 * @return  返回时段末库容（m³）
	 */
	public double get_MoKuRong(double yueChuKuRong, 
			double yueRuKuJingLiuLiang, double yueChuKuJingLiuLiang){
		double yueMoKuRong = yueChuKuRong + yueRuKuJingLiuLiang - yueChuKuJingLiuLiang;
		return yueMoKuRong;
	}
	
	
	
//	//测试
//	public static void main(String[] args) {
//		ShuiLiangPingHeng slph = new ShuiLiangPingHeng();
//		double chuKuRong = 778837838;
//		double ruKuJingLiuLiang = 45572556;
//		double chuKuJingLiuLiang = 83634525;
//		double moKuRong = slph.get_MoKuRong(
//				chuKuRong, ruKuJingLiuLiang, chuKuJingLiuLiang);
//		System.out.println(moKuRong);
//	}
	
}
