import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		FileList fl = new FileList();
		FileFind ff = new FileFind();

		System.out.print("ã�� ��� ( 0 = YES, ELSE = NO): ");
		int find = sc.nextInt(); //ã�� ����� �̿��ϱ� ���ؼ� �Է��� �޽��ϴ�.
		if (find == 0) {
			fl.setFind(find); // FileList Ŭ������ find�� 0���� �������ݴϴ� (0:��� �� �˻�,  ELSE:���)
			System.out.print("ã�� ���� : ");
			String test = sc1.nextLine();
			ff.setfindFile(test); // ã�� ������ �̸��� �������ݴϴ�.

		} else {
			fl.setFind(find);// FileList Ŭ������ find�� 1�� �ƴ� �ٸ� ������ �������ݴϴ� (0:��� �� �˻�, ELSE:���)
		}

		System.out.print("��� �Է� :");
		fl.Filelist(sc1.nextLine()); //���ϰ� ���丮�� ����� ��θ� �����մϴ�.

		System.out.println("=========================");
		if(fl.getResultFile() != null){ // ã�����ٸ� fl.getResultFile()�� ��ȯ ���� ã�� ������ ����Դϴ�.
		System.out.println("ã�� : " + fl.getResultFile()); // ã�� ���� ��� ���
		}else{
			System.out.println("������ ã�� ���߽��ϴ�.");
		}
	}

	public static class FileList {

		private int find = 0;
		private String resultFile;

		FileFind findfile = new FileFind();

		public void Filelist(String path) throws IOException {
			File dir = new File(path); // ��θ� �Է¹ް� File ���·� ������ݴϴ�.
			File[] fileList = dir.listFiles(); // �Է¹��� ��ο��� ������ ����Ʈ�� �迭�� �ֽ��ϴ�.
			try {
				if (find == 0) { // ã�� ����� �����Ǿ��ִٸ� 
					for (int i = 0; i < fileList.length; i++) {
						File file = fileList[i];
						if (file.isDirectory()) { // ���� �������?
							System.out.println("Dir : " + fileList[i]); // �������� ����մϴ�.
							Filelist(file.getAbsolutePath().toString()); // ������ ���� ���ϵ��� ã�� ���� �ٽ� �Լ��� ���ȣ�� �մϴ�.
						} else if (file.isFile()) { // ���� �����̶��
							int intFileName = fileList[i].toString().lastIndexOf("\\"); // ���ϸ��� �����մϴ�.
							String fileName = fileList[i].toString().substring(intFileName + 1);// ���ϸ��� �����մϴ�.
							if (findfile.FileFind(fileName)) { // ���� ���� �˻��ߴ� �����̶��
								resultFile = fileList[i].toString(); // resultFile�� ���� ã�� ������ ��θ� �ֽ��ϴ�.
							}
							System.out.println("  -File : " + fileList[i]);// ���ϸ��� ����մϴ�.

						}
					}
				} else { // ã�� ����� �������� �ʾ��� �� (����) 
					for (int i = 0; i < fileList.length; i++) {
						File file = fileList[i];
						if (file.isDirectory()) { // ���� �������?
							System.out.println("Dir : " + fileList[i]); // �������� ����մϴ�.
							Filelist(file.getAbsolutePath().toString()); // ������ ���� ���ϵ��� ã�� ���� �ٽ� �Լ��� ���ȣ�� �մϴ�.
						} else if (file.isFile()) { // ���� �����̶��
							System.out.println("  -File : " + fileList[i]); // ���ϸ��� ����մϴ�.
						}
					}
				}
			} catch (Exception I) {

			}
		}

		public void setFind(int find) { //�˻� Ȱ��ȭ or ���
			this.find = find;
		}

		public String getResultFile() {
			return resultFile; // �˻� Ȱ��ȭ�� �˻��� ���� ����
		}

	}

	public static class FileFind {
		private static String findFile;

		public boolean FileFind(String fileList) throws IOException {
			if (findFile.equals(fileList)) { // ���ϰ� �˻��� ������ ���ϰ� �̸��� ���ٸ�
				return true; // ���� ��ȯ
			}
			return false;
		}

		public void setfindFile(String findFile) { // �˻��� ���� ����
			this.findFile = findFile;
		}

	}
}
