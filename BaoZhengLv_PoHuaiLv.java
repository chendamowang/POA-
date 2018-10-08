package benKeBiYeSheJi;

public class BaoZhengLv_PoHuaiLv {

	/**
	 * ��ĳ�ֵ��ȵ��ȹ����£��ó��ó�ˮ��վ�������У�
	 * �ڸ�����֤�����£�����ˮ��վ�ı�֤��(�³�������֤����)
	 * @param yueChuLi double����һά���飬�³�������(kW)
	 * @param baoZhengChuLi ��֤����(kW)
	 * @return  �ڸ�����������µ�վ�ı�֤��
	 */
	public double get_BaoZhengLv(double[] yueChuLi, double baoZhengChuLi){
		int length = yueChuLi.length;//�����ܵ�ʱ��
		double number = 0.0;//���ڻ���ڱ�֤������ʱ����
		for(int i = 0; i < yueChuLi.length; i ++){
			if(yueChuLi[i] >= baoZhengChuLi){
				number ++;
			}
		}
		double baoZhengLv = (number / length);
		return baoZhengLv;
	}
	
	/**
	 * ��ĳ�ֵ��ȵ��ȹ����£��ó��ó�ˮ��վ�������У�
	 * �ڸ�����֤�����£�����ˮ��վ�ı�֤��(�³�������֤����)
	 * @param yueChuLi  double����һά���飬�³�������(kW)
	 * @param baoZhengChuLi  ��֤����(kW)
	 * @return �ڸ�����������µ�վ���ƻ���
	 */
	public double get_PoHuaiLv(double[] yueChuLi, double baoZhengChuLi){
		int length = yueChuLi.length;//�����ܵ�ʱ��
		double number = 0.0;//���ڻ���ڱ�֤������ʱ����
		for(int i = 0; i < yueChuLi.length; i ++){
			if(yueChuLi[i] < baoZhengChuLi){
				number ++;
			}
		}
		double poHuaiLv = (number / length);
		return poHuaiLv;
	}
		
//		//����
//		public static void main(String[] args) {		
//			DiaoDuTu ddt = new DiaoDuTu();
//			//���ȹ���
//			double[][] diaoDuTuShuiWei = ddt.getDiaoDuShuiWei_CS();
//			ZongFaDianLiang_POA zfdl = new ZongFaDianLiang_POA();
//			zfdl.set_ShiZiTanShuiKuDiaoDu(diaoDuTuShuiWei);
//			//���³���
//			double[] yueChuLi = zfdl.get_YueChuLi();
//			BaoZhengLv_PoHuaiLv bzlphl = new BaoZhengLv_PoHuaiLv();
//			//��֤����
//			double baoZhengChuLi = 12800;
//			//��֤��
//			double baoZhengLv = bzlphl.get_BaoZhengLv(yueChuLi, baoZhengChuLi);
//			System.out.println(baoZhengLv);
//			//�ƻ���
//			double poHuaiLv = bzlphl.get_PoHuaiLv(yueChuLi, baoZhengChuLi);
//			System.out.println(poHuaiLv);
//		}
		
}
