package benKeBiYeSheJi;

import commonTool.Input_xls_Data;

public class DiaoDuTu {


	Input_xls_Data id = new Input_xls_Data();
	String path, sheetName, colName;

	double[] siShuiWei;	//��ˮλ��m��
	double[] zhengChangXuShuiWei;//������ˮλ��Ѵ��ˮλ��m��
	
	//��0.8����������֤�������������ˮλ����ͬһ����ά������
	//�·ݣ�1-12����������0.8����12800kW��������16000kW����
	double[][] diaoDuShuiWei_CS;

	/**
	 * ������ļ��أ��ѽ���ʼˮ�����ͼ�и��¸�ˮλ����
	 */
	public DiaoDuTu(){
		path = "F:\\�о����׶�\\���Ʊ�ҵ��ƣ�2018����\\ʨ��̲ˮ����Ϣ\\��ʼ����ͼ - ����.xls";
		sheetName = "��ʼ����ͼ";
		colName = "��ˮλ��m��";
		siShuiWei = id.getColumnDatasDouble(path, sheetName, colName);
	}
	/**
	 * ���ظ�����ˮλ��328.5m
	 * @return
	 */
	public double[] read_SiShuiWei(){		
		return siShuiWei;
	}
	/**
	 * ���ظ���������ˮλ /Ѵ��ˮλ��346.3m/341.3m)
	 * @return
	 */
	public double[] read_ZhengChangXuShuiWei() {
		colName = "������ˮλ/Ѵ��ˮλ��m��";
		zhengChangXuShuiWei = id.getColumnDatasDouble(path, sheetName, colName);	
		return zhengChangXuShuiWei;
	}
	
	
	//��ʼ����ˮλ:һά��ʾ�·ݡ���ά��ʾ������0��0.8����������1��������
	public double[][] getDiaoDuShuiWei_CS() {
		colName = "0.8����֤����ʱˮλ��m��";//12800kW
		double[] shuiWei_1 = id.getColumnDatasDouble(path, sheetName, colName);
		colName = "������֤����ʱˮλ��m��";//16000kW
		double[] shuiWei_2 = id.getColumnDatasDouble(path, sheetName, colName);	
		//��������֤��������ˮλ�������ޡ����޷���ͬһ��ά������
		diaoDuShuiWei_CS = new double[12][2];
		for(int i = 0; i < shuiWei_1.length; i ++){
			diaoDuShuiWei_CS[i][0] = shuiWei_1[i];
			diaoDuShuiWei_CS[i][1] = shuiWei_2[i];
		}
		return diaoDuShuiWei_CS;
	}
	
	
	/**
	 * ���������·ݡ���ˮλ������ˮλ�����յ��ȹ���ȷ�����·���������ˮ���磩
	 * @param yueFen int���ͣ��·ݣ�1-12��
	 * @param chuShiShuiWei ��Ӧ���³�ˮλ��m��
	 * @param diaoDuTuShuiWei ����ͼ�ϵĵ���ˮλ�����ӵ����ߣ�1-12�£�����ͬһ��ά������
	 * @return  ����ˮ��վ�ĳ�����kW��
	 */
	public double get_shuiDianZhanYueChuLi(int yueFen,double chuShiShuiWei, double[][] diaoDuTuShuiWei){
//		double baoZhengChuLi_1 = 12800;	//0.8����֤������12800kW��
//		double baoZhengChuLi_2 = 16000;	//������֤������16000kW��
//		double baoZhengChuLi_3 = 28800;	//1.8����֤������28800kW��
		//����ˮ��վ����
		double baoZhengChuLi_1 = 9600;	//0.8����֤������9600kW��
		double baoZhengChuLi_2 = 12000;	//������֤������12000kW��
		double baoZhengChuLi_3 = 21600;	//1.8����֤������21600kW��
		
		//ĳ����ĳһ��ʼˮλ����Ӧ��ˮ��վ����
		double yueChuLi = 0.0;//�������³�ˮλ����ˮλ�������ˮ��վֹͣ���磬���³���Ϊ0.
		//�ɵ���ͼ�еĵ��ȹ���ѡ�����ˮ��վ�ĳ���
		if(chuShiShuiWei < siShuiWei[yueFen-1]){
			yueChuLi = 0.0;//����ֹͣ����
		}else if(chuShiShuiWei < diaoDuTuShuiWei[yueFen-1][0]){
			yueChuLi = baoZhengChuLi_1;//0.8����֤������
		}else if(chuShiShuiWei < diaoDuTuShuiWei[yueFen-1][1]){
			yueChuLi = baoZhengChuLi_2;//������֤������
		}else {
			yueChuLi = baoZhengChuLi_3;//1.8����֤������
		}
		return yueChuLi;		
	}
	
	
//	//����
//	public static void main(String[] args) {
//		DiaoDuTu ddt = new DiaoDuTu();
//		double[] arr2 = ddt.read_SiShuiWei();
//		double[] arr3 = ddt.read_ZhengChangXuShuiWei();
////		for(double d : arr3){
////			System.out.println(d);
////		}
//		double[][] arr = ddt.getDiaoDuShuiWei_CS();
////		for(int i = 0; i < arr.length; i ++){
////			for(int j = 0; j < arr[i].length; j ++){
////				System.out.println(arr[i][j]);
////			}
////			System.out.println("-----------");
////		}
//		int yueFen = 1;
//		double chuShiShuiWei = 346.5;
//		double yueChuLi = ddt.get_shuiDianZhanYueChuLi(yueFen, chuShiShuiWei, arr);
//		System.out.println(yueChuLi);
//	}
	
}
