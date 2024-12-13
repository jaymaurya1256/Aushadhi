import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.jay.aushadhi.ui.components.AushadhiDetailScreen
import dev.jay.aushadhi.ui.components.BottomNavigationBar
import dev.jay.aushadhi.ui.components.DiseaseDetailScreen
import dev.jay.aushadhi.ui.components.ListItemSearch
import dev.jay.aushadhi.ui.components.PatientDetailScreen
import dev.jay.aushadhi.ui.screens.search.SearchViewModel
import dev.jay.aushadhi.ui.screens.search.Type
import dev.jay.aushadhi.utils.ScreenType

@Composable
fun SearchScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }
    val data = viewModel.searchResults.collectAsState()
    Box {
        Column(
            modifier = modifier
                .systemBarsPadding()
                .fillMaxSize()
                .background(Color.White)
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it
                                viewModel.search(query)
                                },
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.systemBarsPadding().padding(bottom = 64.dp)
            ) {
                val itemData = data.value
                items(data.value.size) { index ->
                    ListItemSearch(
                        title = itemData[index].name,
                        description = itemData[index].description,
                        type = itemData[index].type,
                        onClick = {
                            when(itemData[index].type) {
                                Type.AUSHADHI -> {
                                    navController.navigate(AushadhiDetailScreen(id = itemData[index].id.toInt()))
                                }
                                Type.DISEASE -> {
                                    navController.navigate(DiseaseDetailScreen(id = itemData[index].id.toInt()))
                                }
                                Type.PATIENT -> navController.navigate(PatientDetailScreen(id = itemData[index].id))
                            }
                        }
                    )
                }
            }
        }

        BottomNavigationBar(navController, screenType = ScreenType.SEARCH,
            modifier = Modifier
            .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp)),
        placeholder = {
            Text(
                text = "Search",
                style = TextStyle(color = Color.Gray, fontSize = 16.sp)
            )
        },
        colors = TextFieldDefaults.colors(),
        singleLine = true
    )
}


