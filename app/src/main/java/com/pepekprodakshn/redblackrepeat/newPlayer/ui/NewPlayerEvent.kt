package com.pepekprodakshn.redblackrepeat.newPlayer.ui

sealed class NewPlayerEvent {

    class OnNameChanged(name: String) : NewPlayerEvent() {}
}