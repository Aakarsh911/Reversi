package cs3500.reversi.provider.view.gui;

import java.util.List;

import cs3500.reversi.provider.controller.PlayerActionObserver;

interface TPRViewSubject {
  void addPlayerListener(PlayerActionObserver observer);

  void movePressed(List<Integer> hexCoordinates);

  void passPressed();
}
