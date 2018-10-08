package benKeBiYeSheJi;

public class BaoZhengLv_PoHuaiLv {

	/**
	 * 在某种调度调度规则下，得出得出水电站出力序列，
	 * 在给定保证出力下，计算水电站的保证率(月出力＞保证出力)
	 * @param yueChuLi double类型一维数组，月出力序列(kW)
	 * @param baoZhengChuLi 保证出力(kW)
	 * @return  在该组出力序列下电站的保证率
	 */
	public double get_BaoZhengLv(double[] yueChuLi, double baoZhengChuLi){
		int length = yueChuLi.length;//出力总的时长
		double number = 0.0;//等于或大于保证出力的时段数
		for(int i = 0; i < yueChuLi.length; i ++){
			if(yueChuLi[i] >= baoZhengChuLi){
				number ++;
			}
		}
		double baoZhengLv = (number / length);
		return baoZhengLv;
	}
	
	/**
	 * 在某种调度调度规则下，得出得出水电站出力序列，
	 * 在给定保证出力下，计算水电站的保证率(月出力＞保证出力)
	 * @param yueChuLi  double类型一维数组，月出力序列(kW)
	 * @param baoZhengChuLi  保证出力(kW)
	 * @return 在该组出力序列下电站的破坏率
	 */
	public double get_PoHuaiLv(double[] yueChuLi, double baoZhengChuLi){
		int length = yueChuLi.length;//出力总的时长
		double number = 0.0;//等于或大于保证出力的时段数
		for(int i = 0; i < yueChuLi.length; i ++){
			if(yueChuLi[i] < baoZhengChuLi){
				number ++;
			}
		}
		double poHuaiLv = (number / length);
		return poHuaiLv;
	}
		
//		//测试
//		public static void main(String[] args) {		
//			DiaoDuTu ddt = new DiaoDuTu();
//			//调度规则
//			double[][] diaoDuTuShuiWei = ddt.getDiaoDuShuiWei_CS();
//			ZongFaDianLiang_POA zfdl = new ZongFaDianLiang_POA();
//			zfdl.set_ShiZiTanShuiKuDiaoDu(diaoDuTuShuiWei);
//			//各月出力
//			double[] yueChuLi = zfdl.get_YueChuLi();
//			BaoZhengLv_PoHuaiLv bzlphl = new BaoZhengLv_PoHuaiLv();
//			//保证出力
//			double baoZhengChuLi = 12800;
//			//保证率
//			double baoZhengLv = bzlphl.get_BaoZhengLv(yueChuLi, baoZhengChuLi);
//			System.out.println(baoZhengLv);
//			//破坏率
//			double poHuaiLv = bzlphl.get_PoHuaiLv(yueChuLi, baoZhengChuLi);
//			System.out.println(poHuaiLv);
//		}
		
}
