package benKeBiYeSheJi;

public class MuBiaoHanShu {
	
//	/**
//	 * 在保证率下，计算目标函数值
//	 * @param diaoDuTuShuiWei double类型二维，调度图水位
//	 * @param baoZhengChuLi 保证出力（kW）
//	 * @return double类型，目标函数值
//	 */
//	public double get_MuBiaoHanShuZhi_BZL(double[][] diaoDuTuShuiWei,
//			double baoZhengChuLi){
//		ZongFaDianLiang zfdl = new ZongFaDianLiang();
//		zfdl.set_ShiZiTanShuiKuDiaoDu(diaoDuTuShuiWei);
//		double zongFaDianLiang = zfdl.get_ZongFaDianLiang();
//		double[] yueChuLi = zfdl.get_YueChuLi();
//		BaoZhengLv_PoHuaiLv bzl = new BaoZhengLv_PoHuaiLv();
//		double baoZhengLv = bzl.get_BaoZhengLv(yueChuLi, baoZhengChuLi);
//		//保证率放大1000000倍
//		double hanShuZhi = zongFaDianLiang + (baoZhengLv * 1000000);
//		return hanShuZhi;
//	}
	
	/**
	 * 在给定总发电量、保证率、惩罚系数下，计算目标函数值
	 * @param zongFaDianLiang 总发电量（kW·h）
	 * @param baoZhengLv  保证率（0-1.0)
	 * @param chengFaXiShu 惩罚系数
	 * @return double类型，目标函数值
	 */
	public double get_MuBiaoHanShuZhi_BZL(double zongFaDianLiang, 
			double baoZhengLv, double chengFaXiShu){
		double hanShuZhi = zongFaDianLiang - (chengFaXiShu * baoZhengLv);
		return hanShuZhi;
	}
	
	/**
	 * 在破坏率下，计算目标函数值
	 * @param diaoDuTuShuiWei  double类型二维，调度图水位
	 * @param baoZhengChuLi  保证出力（kW）
	 * @return double类型，目标函数值
	 */
//	public double get_MuBiaoHanShuZhi_PHL(double[][] diaoDuTuShuiWei,
//			double baoZhengChuLi){
//		ZongFaDianLiang zfdl = new ZongFaDianLiang();
//		zfdl.set_ShiZiTanShuiKuDiaoDu(diaoDuTuShuiWei);
//		double zongFaDianLiang = zfdl.get_ZongFaDianLiang();
//		double[] yueChuLi = zfdl.get_YueChuLi();
//		BaoZhengLv_PoHuaiLv bzl = new BaoZhengLv_PoHuaiLv();
//		double poHuaLv = bzl.get_PoHuaiLv(yueChuLi, baoZhengChuLi);
//		//保证率放大1000000倍
//		double hanShuZhi = zongFaDianLiang - (poHuaLv * 1000000);
//		return hanShuZhi;
//	}
	/**
	 * 在破坏率下，计算目标函数值
	 * @param zongFaDianLiang 总发电量（kW·h）
	 * @param poHuaiLv  保证率（0-1.0)
	 * @param chengFaXiShu  惩罚系数
	 * @return  double类型，目标函数值
	 */
	public double get_MuBiaoHanShuZhi_PHL(double zongFaDianLiang, 
			double poHuaiLv, double chengFaXiShu){
		double hanShuZhi = zongFaDianLiang - (chengFaXiShu * poHuaiLv);
		return hanShuZhi;
	}
	
	//测试
//	public static void main(String[] args) {
//		DiaoDuTu ddt = new DiaoDuTu();
//		double[][] diaoDuTuShuiWei = ddt.getDiaoDuShuiWei_CS();
//		double baoZhengChuLi = 16000;
//		MuBiaoHanShu mbhs = new MuBiaoHanShu();
//		double hanShuZhi = mbhs.get_MuBiaoHanShuZhi_BZL(diaoDuTuShuiWei, baoZhengChuLi);
//		System.out.println(hanShuZhi);
//		System.out.println("---------------------");
//		double hanShuZhi2 = mbhs.get_MuBiaoHanShuZhi_PHL(diaoDuTuShuiWei, baoZhengChuLi);
//		System.out.println(hanShuZhi2);
//		System.out.println("---------------------");
//	}

}
