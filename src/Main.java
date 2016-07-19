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

		System.out.print("찾기 기능 ( 0 = YES, ELSE = NO): ");
		int find = sc.nextInt(); //찾기 기능을 이용하기 위해서 입력을 받습니다.
		if (find == 0) {
			fl.setFind(find); // FileList 클래스의 find를 0으로 설정해줍니다 (0:출력 및 검색,  ELSE:출력)
			System.out.print("찾을 파일 : ");
			String test = sc1.nextLine();
			ff.setfindFile(test); // 찾을 파일의 이름을 설정해줍니다.

		} else {
			fl.setFind(find);// FileList 클래스의 find를 1이 아닌 다른 값으로 설정해줍니다 (0:출력 및 검색, ELSE:출력)
		}

		System.out.print("경로 입력 :");
		fl.Filelist(sc1.nextLine()); //파일과 디렉토리를 출력할 경로를 설정합니다.

		System.out.println("=========================");
		if(fl.getResultFile() != null){ // 찾아졌다면 fl.getResultFile()의 반환 값은 찾은 파일의 경로입니다.
		System.out.println("찾음 : " + fl.getResultFile()); // 찾을 파일 경로 출력
		}else{
			System.out.println("파일을 찾지 못했습니다.");
		}
	}

	public static class FileList {

		private int find = 0;
		private String resultFile;

		FileFind findfile = new FileFind();

		public void Filelist(String path) throws IOException {
			File dir = new File(path); // 경로를 입력받고 File 형태로 만들어줍니다.
			File[] fileList = dir.listFiles(); // 입력받은 경로에서 파일의 리스트를 배열에 넣습니다.
			try {
				if (find == 0) { // 찾기 기능이 설정되어있다면 
					for (int i = 0; i < fileList.length; i++) {
						File file = fileList[i];
						if (file.isDirectory()) { // 만약 폴더라면?
							System.out.println("Dir : " + fileList[i]); // 폴더명을 출력합니다.
							Filelist(file.getAbsolutePath().toString()); // 폴더에 하위 파일들을 찾기 위해 다시 함수를 재귀호출 합니다.
						} else if (file.isFile()) { // 만약 파일이라면
							int intFileName = fileList[i].toString().lastIndexOf("\\"); // 파일명을 추출합니다.
							String fileName = fileList[i].toString().substring(intFileName + 1);// 파일명을 추출합니다.
							if (findfile.FileFind(fileName)) { // 만약 내가 검색했던 파일이라면
								resultFile = fileList[i].toString(); // resultFile에 내가 찾던 파일의 경로를 넣습니다.
							}
							System.out.println("  -File : " + fileList[i]);// 파일명을 출력합니다.

						}
					}
				} else { // 찾기 기능이 설정되지 않았을 때 (숙제) 
					for (int i = 0; i < fileList.length; i++) {
						File file = fileList[i];
						if (file.isDirectory()) { // 만약 폴더라면?
							System.out.println("Dir : " + fileList[i]); // 폴더명을 출력합니다.
							Filelist(file.getAbsolutePath().toString()); // 폴더에 하위 파일들을 찾기 위해 다시 함수를 재귀호출 합니다.
						} else if (file.isFile()) { // 만약 파일이라면
							System.out.println("  -File : " + fileList[i]); // 파일명을 출력합니다.
						}
					}
				}
			} catch (Exception I) {

			}
		}

		public void setFind(int find) { //검색 활성화 or 출력
			this.find = find;
		}

		public String getResultFile() {
			return resultFile; // 검색 활성화시 검색된 파일 리턴
		}

	}

	public static class FileFind {
		private static String findFile;

		public boolean FileFind(String fileList) throws IOException {
			if (findFile.equals(fileList)) { // 파일과 검색을 설정한 파일과 이름이 같다면
				return true; // 참을 반환
			}
			return false;
		}

		public void setfindFile(String findFile) { // 검색할 파일 설정
			this.findFile = findFile;
		}

	}
}
