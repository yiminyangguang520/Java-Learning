package guru.springframework.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class IngredientControllerTest {

  @Mock
  IngredientService ingredientService;

  @Mock
  UnitOfMeasureService unitOfMeasureService;

  @Mock
  RecipeService recipeService;

  IngredientController controller;

  MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    controller = new IngredientController(ingredientService, recipeService, unitOfMeasureService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyString())).thenReturn(recipeCommand);

    //when
    mockMvc.perform(get("/recipe/1/ingredients"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/list"))
        .andExpect(model().attributeExists("recipe"));

    //then
    verify(recipeService, times(1)).findCommandById(anyString());
  }

  @Test
  public void testShowIngredient() throws Exception {
    //given
    IngredientCommand ingredientCommand = new IngredientCommand();

    //when
    when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).thenReturn(ingredientCommand);

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/show"))
        .andExpect(model().attributeExists("ingredient"));
  }

  @Test
  public void testNewIngredientForm() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId("1");

    //when
    when(recipeService.findCommandById(anyString())).thenReturn(recipeCommand);
    when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

    //then
    mockMvc.perform(get("/recipe/1/ingredient/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/ingredientform"))
        .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attributeExists("uomList"));

    verify(recipeService, times(1)).findCommandById(anyString());

  }

  @Test
  public void testUpdateIngredientForm() throws Exception {
    //given
    IngredientCommand ingredientCommand = new IngredientCommand();

    //when
    when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).thenReturn(ingredientCommand);
    when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/update"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/ingredientform"))
        .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attributeExists("uomList"));
  }

  @Test
  public void testSaveOrUpdate() throws Exception {
    //given
    IngredientCommand command = new IngredientCommand();
    command.setId("3");
    command.setRecipeId("2");

    //when
    when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

    //then
    mockMvc.perform(post("/recipe/2/ingredient")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "")
        .param("description", "some string")
    )
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

  }

  @Test
  public void testDeleteIngredient() throws Exception {

    //then
    mockMvc.perform(get("/recipe/2/ingredient/3/delete")
    )
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/2/ingredients"));

    verify(ingredientService, times(1)).deleteById(anyString(), anyString());

  }
}