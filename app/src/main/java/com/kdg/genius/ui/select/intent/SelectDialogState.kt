package com.kdg.genius.ui.select.intent

sealed class SelectDialogState {
    data object BackDialog: SelectDialogState()
}