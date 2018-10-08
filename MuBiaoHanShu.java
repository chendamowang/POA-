package benKeBiYeSheJi;

public class MuBiaoHanShu {
	
//	/**
//	 * �ڱ�֤���£�����Ŀ�꺯��ֵ
//	 * @param diaoDuTuShuiWei double���Ͷ�ά������ͼˮλ
//	 * @param baoZhengChuLi ��֤������kW��
//	 * @return double���ͣ�Ŀ�꺯��ֵ
//	 */
//	public double get_MuBiaoHanShuZhi_BZL(double[][] diaoDuTuShuiWei,
//			double baoZhengChuLi){
//		ZongFaDianLiang zfdl = new ZongFaDianLiang();
//		zfdl.set_ShiZiTanShuiKuDiaoDu(diaoDuTuShuiWei);
//		double zongFaDianLiang = zfdl.get_ZongFaDianLiang();
//		double[] yueChuLi = zfdl.get_YueChuLi();
//		BaoZhengLv_PoHuaiLv bzl = new BaoZhengLv_PoHuaiLv();
//		double baoZhengLv = bzl.get_BaoZhengLv(yueChuLi, baoZhengChuLi);
//		//��֤�ʷŴ�1000000��
//		double hanShuZhi = zongFaDianLiang + (baoZhengLv * 1000000);
//		return hanShuZhi;
//	}
	
	/**
	 * �ڸ����ܷ���������֤�ʡ��ͷ�ϵ���£�����Ŀ�꺯��ֵ
	 * @param zongFaDianLiang �ܷ�������kW��h��
	 * @param baoZhengLv  ��֤�ʣ�0-1.0)
	 * @param chengFaXiShu �ͷ�ϵ��
	 * @return double���ͣ�Ŀ�꺯��ֵ
	 */
	public double get_MuBiaoHanShuZhi_BZL(double zongFaDianLiang, 
			double baoZhengLv, double chengFaXiShu){
		double hanShuZhi = zongFaDianLiang - (chengFaXiShu * baoZhengLv);
		return hanShuZhi;
	}
	
	/**
	 * ���ƻ����£�����Ŀ�꺯��ֵ
	 * @param diaoDuTuShuiWei  double���Ͷ�ά������ͼˮλ
	 * @param baoZhengChuLi  ��֤������kW��
	 * @return double���ͣ�Ŀ�꺯��ֵ
	 */
//	public double get_MuBiaoHanShuZhi_PHL(double[][] diaoDuTuShuiWei,
//			double baoZhengChuLi){
//		ZongFaDianLiang zfdl = new ZongFaDianLiang();
//		zfdl.set_ShiZiTanShuiKuDiaoDu(diaoDuTuShuiWei);
//		double zongFaDianLiang = zfdl.get_ZongFaDianLiang();
//		double[] yueChuLi = zfdl.get_YueChuLi();
//		BaoZhengLv_PoHuaiLv bzl = new BaoZhengLv_PoHuaiLv();
//		double poHuaLv = bzl.get_PoHuaiLv(yueChuLi, baoZhengChuLi);
//		//��֤�ʷŴ�1000000��
//		double hanShuZhi = zongFaDianLiang - (poHuaLv * 1000000);
//		return hanShuZhi;
//	}
	/**
	 * ���ƻ����£�����Ŀ�꺯��ֵ
	 * @param zongFaDianLiang �ܷ�������kW��h��
	 * @param poHuaiLv  ��֤�ʣ�0-1.0)
	 * @param chengFaXiShu  �ͷ�ϵ��
	 * @return  double���ͣ�Ŀ�꺯��ֵ
	 */
	public double get_MuBiaoHanShuZhi_PHL(double zongFaDianLiang, 
			double poHuaiLv, double chengFaXiShu){
		double hanShuZhi = zongFaDianLiang - (chengFaXiShu * poHuaiLv);
		return hanShuZhi;
	}
	
	//����
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
