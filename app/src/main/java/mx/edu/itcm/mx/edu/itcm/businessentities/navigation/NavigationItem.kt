package mx.edu.itcm.mx.edu.itcm.businessentities.navigation

import android.graphics.drawable.Icon
import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgeCount: Int?= null
)