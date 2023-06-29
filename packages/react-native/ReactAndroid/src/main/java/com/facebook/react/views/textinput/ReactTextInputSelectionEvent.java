/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.views.textinput;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/** Event emitted by EditText native view when the text selection changes. */
/* package */ class ReactTextInputSelectionEvent extends Event<ReactTextInputSelectionEvent> {

  private static final String EVENT_NAME = "topSelectionChange";

  private int mSelectionStart;
  private int mSelectionEnd;
  private int mCursorPositionStartX;
  private int mCursorPositionStartY;
  private int mCursorPositionEndX;
  private int mCursorPositionEndY;

  @Deprecated
  public ReactTextInputSelectionEvent(
      int viewId,
      int selectionStart,
      int selectionEnd,
      int cursorPositionStartX,
      int cursorPositionStartY,
      int cursorPositionEndX,
      int cursorPositionEndY) {
    this(-1, viewId, selectionStart, selectionEnd, cursorPositionStartX, cursorPositionStartY, cursorPositionEndX, cursorPositionEndY);

  }

  public ReactTextInputSelectionEvent(
      int surfaceId,
      int viewId,
      int selectionStart,
      int selectionEnd,
      int cursorPositionStartX,
      int cursorPositionStartY,
      int cursorPositionEndX,
      int cursorPositionEndY) {
    super(surfaceId, viewId);
    mSelectionStart = selectionStart;
    mSelectionEnd = selectionEnd;
    mCursorPositionStartX = cursorPositionStartX;
    mCursorPositionStartY = cursorPositionStartY;
    mCursorPositionEndX = cursorPositionEndX;
    mCursorPositionEndY = cursorPositionEndY;
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Nullable
  @Override
  protected WritableMap getEventData() {
    WritableMap eventData = Arguments.createMap();
    WritableMap selectionData = Arguments.createMap();

    WritableMap startPosition = Arguments.createMap();
    startPosition.putInt("x", mCursorPositionStartX);
    startPosition.putInt("y", mCursorPositionStartY);

    WritableMap endPosition = Arguments.createMap();
    endPosition.putInt("x", mCursorPositionEndX);
    endPosition.putInt("y", mCursorPositionEndY);

    WritableMap selectionPosition = Arguments.createMap();
    selectionPosition.putMap("start", startPosition);
    selectionPosition.putMap("end", endPosition);

    selectionData.putInt("end", mSelectionEnd);
    selectionData.putInt("start", mSelectionStart);
    selectionData.putMap("cursorPosition", selectionPosition);

    eventData.putMap("selection", selectionData);
    return eventData;
  }
}
