package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountTree
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Computer
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.FeatureCard
import com.segnities007.cmp_form_validation.site.components.InfoCard
import com.segnities007.cmp_form_validation.site.components.PlatformCard
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import org.jetbrains.compose.resources.stringResource

private const val GITHUB_URL = "https://github.com/segnities007/cmpformvalidation"

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomePage(onNavigateToDocs: () -> Unit) {
    Column {
        HeroSection(onGetStarted = onNavigateToDocs)

        Spacer(Modifier.height(56.dp))
        CenteredContent {
            SectionHeader(stringResource(Res.string.why_title))
            Spacer(Modifier.height(24.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                FeatureCard(
                    icon = Icons.Rounded.AccountTree,
                    title = stringResource(Res.string.feature_boundary_title),
                    description = stringResource(Res.string.feature_boundary_desc),
                    modifier = Modifier.weight(1f).widthIn(min = 240.dp),
                )
                FeatureCard(
                    icon = Icons.Rounded.Code,
                    title = stringResource(Res.string.feature_compose_title),
                    description = stringResource(Res.string.feature_compose_desc),
                    modifier = Modifier.weight(1f).widthIn(min = 240.dp),
                )
            }
            Spacer(Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                FeatureCard(
                    icon = Icons.Rounded.Lock,
                    title = stringResource(Res.string.feature_stable_title),
                    description = stringResource(Res.string.feature_stable_desc),
                    modifier = Modifier.weight(1f).widthIn(min = 240.dp),
                )
                FeatureCard(
                    icon = Icons.Rounded.Shield,
                    title = stringResource(Res.string.feature_type_safe_title),
                    description = stringResource(Res.string.feature_type_safe_desc),
                    modifier = Modifier.weight(1f).widthIn(min = 240.dp),
                )
            }
        }

        Spacer(Modifier.height(56.dp))
        CenteredContent {
            SectionHeader(stringResource(Res.string.platform_title))
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(Res.string.platform_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp,
            )
            Spacer(Modifier.height(24.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                PlatformCard(Icons.Rounded.PhoneAndroid, stringResource(Res.string.platform_android), stringResource(Res.string.platform_android_desc), Modifier.weight(1f).widthIn(min = 180.dp))
                PlatformCard(Icons.Rounded.PhoneIphone, stringResource(Res.string.platform_ios), stringResource(Res.string.platform_ios_desc), Modifier.weight(1f).widthIn(min = 180.dp))
                PlatformCard(Icons.Rounded.Computer, stringResource(Res.string.platform_desktop), stringResource(Res.string.platform_desktop_desc), Modifier.weight(1f).widthIn(min = 180.dp))
                PlatformCard(Icons.Rounded.Language, stringResource(Res.string.platform_web), stringResource(Res.string.platform_web_desc), Modifier.weight(1f).widthIn(min = 180.dp))
            }
        }

        Spacer(Modifier.height(56.dp))
        CenteredContent {
            SectionHeader(stringResource(Res.string.quick_start_title))
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(Res.string.quick_start_desc),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(20.dp))
            CodeBlock(QUICK_START_CODE, label = "Kotlin")
        }

        Spacer(Modifier.height(56.dp))
        CenteredContent {
            SectionHeader(stringResource(Res.string.architecture_title))
            Spacer(Modifier.height(24.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoCard("validation-core", stringResource(Res.string.module_core_desc))
                InfoCard("validation-compose", stringResource(Res.string.module_compose_desc))
            }
        }
        Spacer(Modifier.height(72.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HeroSection(onGetStarted: () -> Unit) {
    val extra = LocalExtraColors.current
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(extra.heroGradientStart, extra.heroGradientEnd)))
            .padding(vertical = 80.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.widthIn(max = 960.dp).fillMaxWidth().padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Surface(color = extra.heroBadgeBg, shape = RoundedCornerShape(16.dp)) {
                Text(
                    text = stringResource(Res.string.hero_badge),
                    color = extra.heroBadgeText,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                )
            }
            Text(stringResource(Res.string.hero_title), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = extra.heroTitle, textAlign = TextAlign.Center)
            Text(stringResource(Res.string.hero_subtitle), style = MaterialTheme.typography.titleLarge, color = extra.heroSubtitle, textAlign = TextAlign.Center, lineHeight = 32.sp)
            Spacer(Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Surface(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp), modifier = Modifier.clickable(onClick = onGetStarted)) {
                    Text(stringResource(Res.string.hero_get_started), color = Color.White, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp))
                }
                Surface(color = Color.Transparent, shape = RoundedCornerShape(8.dp), border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary), modifier = Modifier.clickable { uriHandler.openUri(GITHUB_URL) }) {
                    Text(stringResource(Res.string.hero_github), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp))
                }
            }
        }
    }
}

private val QUICK_START_CODE = """
val email = rememberValidatedField(
    rules = persistentListOf(required(), email())
)

OutlinedTextField(
    value = email.value,
    onValueChange = email::onValueChange,
    label = { Text("Email") },
    isError = email.showErrors && !email.result.isValid,
    supportingText = { ValidationSupportingText(email) },
    modifier = Modifier.validation(email)
)
""".trimIndent()
