//package com.javarush.task.task35.task3513;
//
//import org.junit.jupiter.api.Test;
//
//class ModelTest {
//	Tile[][] testTiles;
//	private Model model = new Model();
//
//	{
//		for(int i = 0; i < 100; i++) {
////			model.addTile();
//		}
//
//		testTiles = model.getGameTiles();
//		//		for(Tile[] tiles: testTiles
//		//		    ) {
//		//			mergeTilesTest(tiles);
//		//		}
//	}
//
//	@Test
//	void turnTilesTest() {
//
//		for(Tile[] arr : testTiles
//		) {
//			for(Tile t : arr
//			) {
//				System.out.print(t.value + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("***************");
//
//		model.right();
//
//		for(Tile[] arr : testTiles
//		) {
//			for(Tile t : arr
//			) {
//				System.out.print(t.value + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("***************");
//	}
//
//	@Test
//	void canMoveTest() {
//
//		for(Tile[] arr : testTiles
//		) {
//			for(Tile t : arr
//			) {
//				System.out.print(t.value + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("***************");
//
//		System.out.println(model.canMove());
//
//		for(Tile[] arr : testTiles
//		) {
//			for(Tile t : arr
//			) {
//				System.out.print(t.value + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("***************");
//	}
//}
//
////	@org
////	.junit.jupiter.api.Test
////	void
////	compressTilesTest(Tile[] tiles) {
////
////		for(Tile arr : tiles
////		) {
////			System.out.print(arr.value + " ");
////
////		}
////
////		model.compressTiles(tiles);
////		System.out.println("***************");
////
////
////		for(Tile arr : tiles
////		) {
////
////			System.out.print(arr.value + " ");
////
////		}
////		System.out.println("*_*_*_*_*_*_*_*_*_*");
////	}
////
////
////	@org
////	.junit.jupiter.api.Test
////	void
////	mergeTilesTest(Tile[] tiles) {
////
////		model.compressTiles(tiles);
////
////		for(Tile arr : tiles
////		) {
////			System.out.print(arr.value + " ");
////		}
////
////
////		model.mergeTiles(tiles);
////		System.out.println("/////////////////");
////
////
////		for(Tile arr : tiles
////		) {
////			System.out.print(arr.value + " ");
////		}
////		System.out.println("------------------");
////	}
