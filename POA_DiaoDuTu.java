package benKeBiYeSheJi;

import commonMath.MathFunction;

public class POA_DiaoDuTu {
	
	public static void main(String[] args) {
		DiaoDuTu ddt = new DiaoDuTu();//����ͼ
		ZongFaDianLiang_POA zfdlpoa = new ZongFaDianLiang_POA();//�ܷ�����
		BaoZhengLv_PoHuaiLv bzlphl = new BaoZhengLv_PoHuaiLv();//��֤��-�ƻ���
		MuBiaoHanShu mbhs = new MuBiaoHanShu();//Ŀ�꺯��
		MathFunction mf = new MathFunction();//�Զ�ά�����и�Ԫ�ؽ������
		DiaoDuDianLiSan_OneMonth dddlsom = new DiaoDuDianLiSan_OneMonth();//��һ���µĵ��ȵ������ɢ���ж�
		
		//����ʼ�����߸�ֵ�������������ߺ��Ż�������
		double[][] diaoDuTu_CS = ddt.getDiaoDuShuiWei_CS();
		double[][] diaoDuTu_temp = new double[diaoDuTu_CS.length][2];
		double[][] diaoDuTu_YH = new double[diaoDuTu_CS.length][2];
		for(int i = 0; i < diaoDuTu_CS.length; i ++){
			for(int j = 0; j < diaoDuTu_CS[i].length; j ++){
				diaoDuTu_temp[i][j] = diaoDuTu_CS[i][j];
				diaoDuTu_YH[i][j] = diaoDuTu_CS[i][j];
			}
		}	
		//�ͷ�ϵ��
		double chengFaXiShu = 100000000.0;
		//��֤����
//		double baoZhengChuLi = 16000;	
		double baoZhengChuLi = 12000;//����
		//��ʼ�ܷ���������ʼ�³�������ʼ�ƻ��ʡ���ʼĿ�꺯��ֵ
		zfdlpoa.set_ShiZiTanShuiKuDiaoDu(diaoDuTu_CS);
		double zongFaDianLiang_CS = zfdlpoa.get_ZongFaDianLiang();
		double[] yueChuLi_CS = zfdlpoa.get_YueChuLi();
		double poHuaiLv_CS = bzlphl.get_PoHuaiLv(yueChuLi_CS, baoZhengChuLi);
		double muBiaoHanShuZhi_CS = mbhs.get_MuBiaoHanShuZhi_PHL(
				zongFaDianLiang_CS, poHuaiLv_CS, chengFaXiShu);
		System.out.println(poHuaiLv_CS);
		System.out.println(muBiaoHanShuZhi_CS);
		System.out.println(zongFaDianLiang_CS);
		System.out.println("-----------");
		//����ʼĿ�꺯��ֵ��ֵ������Ŀ�꺯��ֵ
		double muBiaoHanShuZhi_max = muBiaoHanShuZhi_CS;
		double poHuaiLv = poHuaiLv_CS;//��Ӧ�ƻ���
		double zongFaDianLiang = zongFaDianLiang_CS;//��Ӧ�ܷ�����
		//ˮλԼ����ÿ���µ���ɢ��Ӧ������ˮλ��������ˮλ֮��
		double[] siShuiWei_Month = ddt.read_SiShuiWei();
		double[] zhengChangXuShuiWei_Month = ddt.read_ZhengChangXuShuiWei();
		//��ʼ��������������ʱ�豣֤�������ܸ���������ˮλ����ˮλ
		//�ڳ�ˮλ����������ˮλ����ˮλ֮��ʱ���������ܸ��ǡ�
		double step = 6;
		//�����任����
		int number = 10;
//		int number = 20;
//		int number = 100;	
//		int number = 200;
		//���任�����£�ȫ���Ż����ܷ���������Ӧ�ƻ��ʡ���Ӧ�ܷ�����		
		double[] step_arr = new double[number];
		double[] muBiaoHanShuZhi_maxArr = new double[number];
		double[] poHuaiLv_arr = new double[number];
		double[] zongFaDianLiang_arr = new double[number];
		
		for(int num = 0; num < number; num ++){//��α仯����
			System.out.println("�����仯" + num + "��");
			step_arr[num] = step - (step/number)*num;
			//��1����12�£������Ż�����ͼ��Ȼ�󲢷��ظ��·ݶ�Ӧ�������ֵ������Ӧ�ĵ�����1��������2
			for(int monthNum = 0; monthNum < 12; monthNum ++){//�����Ż����ȵ�
				//��һ���µĵ��ȵ���е�������ϣ��ó����µ����б��õ��ȵ�
				//��������3������������3����
				double[][] diaoDuTu_LS_OneMonth= dddlsom.get_diaoDuLiSanDian_ZuHe(step_arr[num],
						diaoDuTu_temp[monthNum]);
				double[][] diaoDuTu_LS_OneMonthNew = dddlsom.get_diaoDuDianPanDuan(diaoDuTu_LS_OneMonth,
						siShuiWei_Month[monthNum], zhengChangXuShuiWei_Month[monthNum]);

				//��ÿ���µĶ���������ȵ��У�ѡ�����ŵ��ȵ㣬�����������Ŀ��ֵ������ܷ����������֤��
				for(int i = 0; i < diaoDuTu_LS_OneMonthNew.length; i ++){	
					diaoDuTu_temp[monthNum][0] = diaoDuTu_LS_OneMonthNew[i][0];
					diaoDuTu_temp[monthNum][1] = diaoDuTu_LS_OneMonthNew[i][1];
					zfdlpoa.set_ShiZiTanShuiKuDiaoDu(diaoDuTu_temp);
					double[] yueChuLi_temp = zfdlpoa.get_YueChuLi();
					double poHuaiLv_temp = bzlphl.get_PoHuaiLv(yueChuLi_temp, baoZhengChuLi);
//					if(poHuaiLv_temp <= poHuaiLv_CS){//�µ���ͼ���ƻ���Ӧ������ԭ����ͼ���ƻ���
					//�µ���ͼ���ƻ���Ӧ������0.15
					if(poHuaiLv_temp <= 0.15){
						//�ƻ����𽥼�С
						if(poHuaiLv_temp <= poHuaiLv){
							double zongFaDianLiang_temp = zfdlpoa.get_ZongFaDianLiang();
							double muBiaoHanShuZhi_temp = mbhs.get_MuBiaoHanShuZhi_PHL(zongFaDianLiang_temp, 
									poHuaiLv_temp, chengFaXiShu);						
							if(muBiaoHanShuZhi_temp >= muBiaoHanShuZhi_max){//��Ŀ�꺯��Ӧ��������һ�ε�Ŀ�꺯��
								muBiaoHanShuZhi_max = muBiaoHanShuZhi_temp;
								poHuaiLv = poHuaiLv_temp;
								zongFaDianLiang = zongFaDianLiang_temp;
								//��ȫ��Ŀ�꺯��ֵ����µĵ���ˮλΪ����ˮλ
								diaoDuTu_YH[monthNum][0] = diaoDuTu_temp[monthNum][0];
								diaoDuTu_YH[monthNum][1] = diaoDuTu_temp[monthNum][1];
							}
						}
					}
				}
				//�����µ����ŵ����ߴ�������������������
				diaoDuTu_temp[monthNum][0] = diaoDuTu_YH[monthNum][0];
				diaoDuTu_temp[monthNum][1] = diaoDuTu_YH[monthNum][1];	
			}
			poHuaiLv_arr[num] = poHuaiLv;
			muBiaoHanShuZhi_maxArr[num] = muBiaoHanShuZhi_max;
			zongFaDianLiang_arr[num] = zongFaDianLiang;
		}
		System.out.println("�Ż������仯��");
		for(double d : step_arr){
			System.out.println(d);
		}
		System.out.println("��Ӧ�ƻ��ʱ仯��");
		for(double d : poHuaiLv_arr){
			System.out.println(d);
		}
		System.out.println("�Ż���Ŀ�꺯��ֵ�仯��");
		for(double d : muBiaoHanShuZhi_maxArr){
			System.out.println(d);
		}
		System.out.println("��Ӧ�ܷ������仯��");
		for(double d : zongFaDianLiang_arr){
			System.out.println(d);
		}
		System.out.println("�����Ż������ߣ�");
		for(int i = 0; i < 2; i ++){
			System.out.println("������"+(i+1));
			for(int j = 0; j < diaoDuTu_temp.length; j ++){
				System.out.println(diaoDuTu_temp[j][i]);
			}
		}
	}
}
