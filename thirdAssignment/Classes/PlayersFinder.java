package Classes;

import java.awt.Point;

import Interfaces.IPlayersFinder;

public class PlayersFinder implements IPlayersFinder {
	
	
  private boolean isCovered(final Point[] coveredIndices, final int size, final int x, final int y) {
    for (int i = 0; i < size; i++) {
      if (coveredIndices[i].x == x && coveredIndices[i].y == y) {
        return true; 
      }
    }
    return false;
  }
	
  private int catchObject(final String[] photo, final int team, final Point[] objectPoints,
      final Point[] coveredIndices, final int[] countersArray, final int x, final int y) {
    int pixelsCounter = 1;
    objectPoints[countersArray[0]++] = new Point(x, y);
    coveredIndices[countersArray[1]++] = new Point(x, y);
    int charactersNum = photo[0].length();
	if (y > 0 && photo[y-1].charAt(x) == Character.forDigit(team, 10) && !isCovered(coveredIndices, countersArray[1], x, y-1)) {
		pixelsCounter += catchObject(photo, team, objectPoints, coveredIndices, countersArray, x, y-1);
	}
	if (y < photo.length-1 && photo[y+1].charAt(x) == Character.forDigit(team, 10) && !isCovered(coveredIndices, countersArray[1], x, y+1)) {
		pixelsCounter += catchObject(photo, team, objectPoints, coveredIndices, countersArray, x, y+1);
	}
	if (x > 0 && photo[y].charAt(x-1) == Character.forDigit(team, 10) && !isCovered(coveredIndices, countersArray[1], x-1, y)) {
		pixelsCounter += catchObject(photo, team, objectPoints, coveredIndices, countersArray, x-1, y);
	}
	if (x < charactersNum-1 && photo[y].charAt(x + 1) == Character.forDigit(team, 10) 
			&& !isCovered(coveredIndices, countersArray[1], x + 1, y)) {
		pixelsCounter += catchObject(photo, team, objectPoints, coveredIndices, countersArray, x+1, y);
	}
	return pixelsCounter;
	}
	private Point getCenter(final Point[] objectPoints, final int pointsNumber) {
		int maxX, minX, maxY, minY;
		maxX = objectPoints[0].x;
		minX = objectPoints[0].x;
		maxY = objectPoints[0].y;
		minY = objectPoints[0].y;
		for (int i = 1; i < pointsNumber; i++) {
			if (maxX < objectPoints[i].x) {
				maxX = objectPoints[i].x;
			}
			if (maxY < objectPoints[i].y) {
				maxY = objectPoints[i].y;
			}
			if (minX > objectPoints[i].x) {
				minX = objectPoints[i].x;
			}
		}
		minX *= 2;
		maxX *= 2;
		minY *= 2;
		maxY *= 2;
		maxX += 2;
		maxY += 2;
		int cenY = (maxY + minY) / 2;
		int cenX = (maxX + minX) / 2;
		Point center = new Point(cenX, cenY);
		return center;
	}
	@Override
	public final Point[] findPlayers(final String[] photo, final int team, final int threshold) {
		if (photo == null || photo.length == 0) {
			return null;
		}
		int eleNum = photo.length;
		int charsNum = photo[0].length();
		Point[] objectPoints = new Point[eleNum * charsNum];
		Point[] coveredIndices = new Point[eleNum * charsNum];
		Point[] centersPoints = new Point[eleNum * charsNum / threshold];
		int objectArea = 0;
		// 0 index for Object points number
		// 1 index  for Covered indices number
		// 2 index  for Centers points number
		int[] countersArray = {0, 0, 0};
		for (int i = 0; i < eleNum; i++) {
			for (int j = 0; j < charsNum; j++) {
				if (!isCovered(coveredIndices, countersArray[1], j, i) && photo[i].charAt(j) == Character.forDigit(team, 10)) {
					countersArray[0] = 0;
					objectArea = catchObject(photo, team, objectPoints, coveredIndices, countersArray, j, i);
					if(objectArea * 4 >= threshold) {
						centersPoints[countersArray[2]++] = getCenter(objectPoints, countersArray[0]);
					}
				}
			}
		}
		// Sorting
		int n = countersArray[2]; 
		for (int i = 1; i < n; ++i) { 
            Point key = centersPoints[i]; 
            int j = i - 1; 
            while (j >= 0 && (centersPoints[j].x > key.x || 
            	(centersPoints[j].x == key.x && centersPoints[j].y > key.y ))) { 
            	centersPoints[j + 1] = centersPoints[j]; 
                j = j - 1; 
            } 
            centersPoints[j + 1] = key; 
		}
		Point[] players = new Point[n];
		for (int i = 0; i < n; ++i) { 
            players[i] = centersPoints[i];
		}
		return players;
	}

}
