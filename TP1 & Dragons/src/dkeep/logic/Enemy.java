package dkeep.logic;

import java.util.Random;

public class Enemy {
	private static final char OGRE = '0';
	private static final char ROOKIE_GUARD = 'G';
	private static final char DRUNKEN_GUARD = 'D';

	public static char[][] moveEnemy(char[][] a) { // Retorna o mapa modificado ap�s carater.
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == OGRE) {
					a = moveOgre(a);
				}
				else if (a[i][j] == ROOKIE_GUARD) {
					a = moveGuard(a,'G');
				}
				else if (a[i][j] == DRUNKEN_GUARD) {
					a = moveGuard(a,'D');
				}
			}
		}

		return a;
	}

	public static char[][] moveGuard(char[][] a, char z) { // Verifica se � rookie ou drunken
		int row = 0, col = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == z) {
					row = i;
					col = j;
				}
			}
		}
		System.out.println(z + " ROW " + row + " COL " + col);
		if (z == 'G') {
			int rowg = row, colg = col;


			if (rowg == 1 && colg == 8) {
				a[rowg][colg] = ' ';
				a[rowg][colg - 1] = 'G';
			} else if (rowg >= 1 && colg == 7 && rowg < 5) {
				a[rowg][colg] = ' ';
				a[rowg + 1][colg] = 'G';
			} else if (rowg == 5 && colg <= 7 && colg > 1) {
				a[rowg][colg] = ' ';
				a[rowg][colg - 1] = 'G';
			} else if (rowg == 5 && colg == 1) {
				a[rowg][colg] = ' ';
				a[rowg + 1][colg] = 'G';
			} else if (rowg == 6 && colg >= 1 && colg < 8) {
				a[rowg][colg] = ' ';
				a[rowg][colg + 1] = 'G';
			} else if (rowg > 1 && rowg <= 6 && colg == 8) {
				a[rowg][colg] = ' ';
				a[rowg - 1][colg] = 'G';
			}
		}

		else if (z == 'D') {
			boolean isValid = false;
			String s = "wasd";
			while (!isValid) {
				Random rand = new Random();
				int index = rand.nextInt(s.length());
				char c = s.charAt(index);
				char result;
				
				if (c == 'w') {
					result = GameLogic.next(a[row - 1][col]);
					System.out.println(result);
				} else if (c == 'a') {
					result = GameLogic.next(a[row][col - 1]);
					System.out.println(result);
				} else if (c == 's') {
					result = GameLogic.next(a[row + 1][col]);
					System.out.println(result);
				} else if (c == 'd') {
					result = GameLogic.next(a[row][col + 1]);
					System.out.println(result);
				} else {
					result = 'N';
				}
				if (result == 'X') {
					continue;
				}
				else if (result == 'H') {
					if (c == 'w' && a[row - 1][col] != 'X' && a[row - 1][col] != 'S' && a[row - 1][col] != 'I' && a[row - 1][col] != 'K') {
						a[row][col] = ' ';
						a[row - 1][col] = 'D';
						isValid = true;
					}

					else if (c == 'a' && a[row][col - 1] != 'X' && a[row][col - 1] != 'S' && a[row][col - 1] != 'I'&& a[row][col -1] != 'K') {
						a[row][col] = ' ';
						a[row][col - 1] = 'D';
						isValid = true;
					}

					else if (c == 's' && a[row + 1][col] != 'X' && a[row + 1][col] != 'S' && a[row + 1][col] != 'I' && a[row + 1][col] != 'K') {
						a[row][col] = ' ';
						a[row + 1][col] = 'D';
						isValid = true;

					} else if (c == 'd' && a[row][col + 1] != 'X' && a[row][col + 1] != 'S' && a[row][col + 1] != 'I' && a[row][col+1] != 'K') {
						a[row][col] = ' ';
						a[row][col + 1] = 'D';
						isValid = true;
					}

				}
				

			}

		}

		return a;

	}

	


	public static char[][] moveOgre(char[][] a) {

		String s = "wasd";
		boolean restoreKey = false;
		Random rand = new Random();
		int index = rand.nextInt(s.length());
		char c = s.charAt(index);
		char result;

		int row = 0, col = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == '0') {
					row = i;
					col = j;
				}
			}
		}

		System.out.println("Ogre: " + c);

		if (c == 'w') {
			result = GameLogic.next(a[row - 1][col]);
			System.out.println(result);
		} else if (c == 'a') {
			result = GameLogic.next(a[row][col - 1]);
			System.out.println(result);
		} else if (c == 's') {
			result = GameLogic.next(a[row + 1][col]);
			System.out.println(result);
		} else if (c == 'd') {
			result = GameLogic.next(a[row][col + 1]);
			System.out.println(result);
		} else {
			result = 'N';
		}

		if (result == 'H') {
			if (c == 'w' && a[row - 1][col] != 'X' && a[row - 1][col] != 'S' && a[row - 1][col] != 'I') {
				a[row][col] = ' ';
				a[row - 1][col] = '0';
			}

			else if (c == 'a' && a[row][col - 1] != 'X' && a[row][col - 1] != 'S' && a[row][col - 1] != 'I') {

				// ELIMINEI O RESTORE KEY, porque como isto acontece antes do
				// movimento do ogre, se o ogre se puser em cima, vai ser
				// colocado outra vez o 'k', mas o ogre sobrep�e-se logo a isso,
				// por isso, simplesmente impedi-o de se p�r na chave.

				a[row][col] = ' ';
				a[row][col - 1] = '0';
			}

			else if (c == 's' && a[row + 1][col] != 'X' && a[row + 1][col] != 'S' && a[row + 1][col] != 'I') {

				if (restoreKey) {
					a[1][7] = 'k';
					restoreKey = false;
				} else {
					a[row][col] = ' ';
				}
				a[row + 1][col] = '0';
			} else if (c == 'd' && a[row][col + 1] != 'X' && a[row][col + 1] != 'S' && a[row][col + 1] != 'I') {
				a[row][col] = ' ';
				a[row][col + 1] = '0';
			}

		}

		else if (result == 'E') {
			a[row][col] = ' ';
			a[row + 1][col] = '0';
		}

		return a;

	}

	public static char[][] placeEnemy(char[][] a, char c) {
		a[1][8] = c;
		return a;
	}

}
