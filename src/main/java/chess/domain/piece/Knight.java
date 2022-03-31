package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Knight extends Piece {

    private static final double SCORE = 2.5;
    private static final BiPredicate<Integer, Integer> movingCondition =
            (rankMove, fileMove) ->
                    (fileMove == 2 && rankMove == 1) || (fileMove == 1 && rankMove == 2);

    public Knight(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, movingCondition, true);
        return new Knight(teamColor, targetPosition);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
