package com.vinaynandikanti.imeivalidation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ImeiControllerTests {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testComputeImeiPost() throws Exception {
        mockMvc.perform(post("/result").param("digitSequence", "92480")).andExpect(status().isOk());

        mockMvc.perform(post("/result").param("digitSequence", "292723200000000021")).andExpect(status().isOk());

        mockMvc.perform(post("/result").param("digitSequence", "855")).andExpect(status().isOk());

        mockMvc.perform(post("/result").param("digitSequence", "00000000")).andExpect(status().isOk());

        mockMvc.perform(post("/result").param("digitSequence", "000000001")).andExpect(status().isOk());
    }

    @Test
    public void testComputeImeiGet() throws Exception {
        mockMvc.perform(get("/result").param("digitSequence", "92480"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("digitSequence", "92480"))
                .andExpect(model().attribute("checkDigit", 3))
                .andExpect(model().attribute("isValid", false));
    }
}
