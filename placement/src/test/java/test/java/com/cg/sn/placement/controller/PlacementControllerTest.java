package test.java.com.cg.sn.placement.controller;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cg.sn.placement.Controller.PlacementController;
import com.cg.sn.placement.Entity.Placement;
import com.cg.sn.placement.Service.PlacementService;




@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {PlacementController.class,})
public class PlacementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlacementService placementService;

    @InjectMocks
    private PlacementController placementController;

    @Test
    public void testGetAllPlacements() throws Exception {
        // Prepare test data
        Placement placement1 = new Placement();
        placement1.setId(1L);
        placement1.setTitle("Placement 1");

        Placement placement2 = new Placement();
        placement2.setId(2L);
        placement2.setTitle("Placement 2");

        List<Placement> placements = Arrays.asList(placement1, placement2);

        // Mock the service method
        Mockito.when(placementService.getAllPlacements()).thenReturn(placements);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/placement/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title", Matchers.is("Placement 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].title", Matchers.is("Placement 2")));

        // Verify that the service method was called
        Mockito.verify(placementService, Mockito.times(1)).getAllPlacements();
    }

}
