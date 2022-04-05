package com.example.space.presentation.navigation

import com.example.space.presentation.MainActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator


class CustomNavigator(
    activity: MainActivity,
    containerID: Int,
    private val changeScreenCallback: (Int) -> Unit
) : AppNavigator(activity, containerID) {
    private val stackCount: Int
        get() = try {
            localStackCopy.size + 1
        } catch (e: Exception) {
            fragmentManager.backStackEntryCount + 1
        }

    override fun applyCommands(commands: Array<out Command>) {
        super.applyCommands(commands)
        changeScreenCallback(stackCount)
    }
}