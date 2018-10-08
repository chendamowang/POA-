package benKeBiYeSheJi;

import commonMath.MathFunction;

public class DiaoDuDianLiSan_OneMonth {

	public double[][] get_diaoDuLiSanDian_ZuHe(double step_temp, double[] diaoDuTu_temp){
		//�����飬��ÿ�����ȵ㰴ָ����������������3������������3����
		double[][] diaoDuDian_LS = new double[2][7];
		for(int j = 0; j < 7; j ++){
			if(j < 4){//��������2��
				diaoDuDian_LS[0][j] = diaoDuTu_temp[0] - j * step_temp;
				diaoDuDian_LS[1][j] = diaoDuTu_temp[1] - j * step_temp;
			}if(j > 3){//��������2��
				diaoDuDian_LS[0][j] = diaoDuTu_temp[0] + (j-3) * step_temp;
				diaoDuDian_LS[1][j] = diaoDuTu_temp[1] + (j-3) * step_temp;
			}
		}
		MathFunction mf = new MathFunction();
		//��ϣ���ͬһ�·������������ϵ��ĸ���������
		double[][] diaoDuTu_LS_OneMonth = mf.combination_TwoArray(diaoDuDian_LS);
		return diaoDuTu_LS_OneMonth;
	}
	
	public double[][] get_diaoDuDianPanDuan(double[][] diaoDuTu_LS_OneMonth,
			double siShuiWei_Month, double zhengChangXuShuiWei_Month){
		//�жϣ�
		for(int i = 0; i < diaoDuTu_LS_OneMonth.length; i ++){
			//����ÿ���£�������1����ɢ�����С�ڵ��ڵ�����2��
			if(diaoDuTu_LS_OneMonth[i][0] > diaoDuTu_LS_OneMonth[i][1]){
//				//���������1���ڵ�����2����ѵ�����1����������2λ��
//				diaoDuTu_LS_OneMonth[i][0] = diaoDuTu_LS_OneMonth[i][1];
				//���������1���ڵ�����2����ѵ�����1����������2λ��
				diaoDuTu_LS_OneMonth[i][1] = diaoDuTu_LS_OneMonth[i][0];
			}
			//��ɢ����봦����ˮλ��������ˮλ֮��
			if(diaoDuTu_LS_OneMonth[i][0] < siShuiWei_Month){
				//���������1������ˮλ����ѵ�����1������ˮλ
				diaoDuTu_LS_OneMonth[i][0] = siShuiWei_Month;
			}else if (diaoDuTu_LS_OneMonth[i][0] > zhengChangXuShuiWei_Month){
				//���������1����������ˮλ����ѵ�����1����������ˮλ
				diaoDuTu_LS_OneMonth[i][0] = zhengChangXuShuiWei_Month;
			}
			if(diaoDuTu_LS_OneMonth[i][1] < siShuiWei_Month){
				//���������2������ˮλ����ѵ�����1������ˮλ
				diaoDuTu_LS_OneMonth[i][1] = siShuiWei_Month;
			}else if (diaoDuTu_LS_OneMonth[i][1] > zhengChangXuShuiWei_Month){
				//���������2����������ˮλ����ѵ�����2����������ˮλ
				diaoDuTu_LS_OneMonth[i][1] = zhengChangXuShuiWei_Month;
			}
		}
		return diaoDuTu_LS_OneMonth;
	}
	
	
//	//����
//	public static void main(String[] args) {
//		DiaoDuDianLiSan_oneMonth dddlsm = new DiaoDuDianLiSan_oneMonth();
//		double step_temp = 10;
//		double[] diaoDuTu_temp = {335, 346};
//		double[][] arr = dddlsm.get_diaoDuLiSanDian(step_temp, diaoDuTu_temp);
//		for(int i = 0; i < arr.length; i ++){
//			for(int j = 0; j < arr[i].length; j ++){
//				System.out.println(arr[i][j]);
//			}
//			System.out.println("-------");
//		}
//		double siShuiWei_Month = 328.5;
//		double zhengChangXuShuiWei_Month = 346.3;
//		double[][] arr2 = dddlsm.get_diaoDuDianPanDuan(arr, 
//				siShuiWei_Month, zhengChangXuShuiWei_Month);
////		for(int i = 0; i < arr2.length; i ++){
////			for(int j = 0; j < arr2[i].length; j ++){
////				System.out.println(arr2[i][j]);
////			}
////			System.out.println("-------");
////		}
//		
//	}
}
