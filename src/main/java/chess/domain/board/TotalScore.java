package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TotalScore {

    private static final double SAME_FILE_PAWN_SUBTRACT_VALUE = 0.5;

    private TotalScore() {
    }

    public static double getTotalPoint(final List<Piece> pieces) {
        double totalPoint = getDefaultPoints(pieces);
        final List<Pawn> pawns = findPawns(pieces);

        for (File file : File.values()) {
            totalPoint -= countSameFilePawn(pawns, file) * SAME_FILE_PAWN_SUBTRACT_VALUE;
        }
        return totalPoint;
    }

    private static List<Pawn> findPawns(final List<Piece> pieces) {
        return pieces.stream()
                .filter(Piece::isPawn)
                .map(Pawn.class::cast)
                .collect(Collectors.toList());
    }

    private static double getDefaultPoints(final List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static int countSameFilePawn(final List<Pawn> pawns, final File file) {
        final int sameFilePawnCount = (int) pawns.stream()
                .map(Pawn.class::cast)
                .filter(pawn -> pawn.isInFile(file))
                .count();
        if (sameFilePawnCount > 1) {
            return sameFilePawnCount;
        }
        return 0;
    }
}
