package com.pharma.appointments;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pharma.appointments.controllers.AppointmentController;
import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.models.AppointmentType;
import com.pharma.appointments.models.ReasonType;
import com.pharma.appointments.repositories.AppointmentRepository;
import com.pharma.appointments.services.AppointmentService;
import com.pharma.appointments.services.AppointmentTypeService;
import com.pharma.appointments.services.ReasonTypeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppointmentsApplication.class)
@WebMvcTest(AppointmentController.class)
class AppointmentsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentTypeService appointmentTypeService;
    @MockBean
    private ReasonTypeService reasonTypeService;
    @MockBean
    private AmqpTemplate rabbitTemplate;
    @MockBean
    private AppointmentRepository appointmentRepository;
    @MockBean
    private AppointmentService appointmentService;

    private final Gson gson = new Gson();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void contextLoads() {
        assertThat(appointmentService).isNotNull();
    }

    @Test
    public void getAllAppointmentsAPI()
            throws Exception {
        Appointment appointment1 = new Appointment();
        List<Appointment> allAppointments = new ArrayList<>();
        allAppointments.add(appointment1);

        given(appointmentService.getAllAppointments()).willReturn(allAppointments);
        mvc.perform(MockMvcRequestBuilders
                .get("/appointments/getall")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    public void getAllAppointmentByEmployeeIdAPI()
            throws Exception {
        Appointment appointment1 = new Appointment(1, new Date(), new Date(), new AppointmentType(), new ReasonType(), "reason", "attention", new Date());
        Appointment appointment2 = new Appointment(2, new Date(), new Date(), new AppointmentType(), new ReasonType(), "reason", "attention", new Date());
        List<Appointment> allAppointments = new ArrayList<>();
        allAppointments.add(appointment1);
        allAppointments.add(appointment2);
        String json = gson.toJson(allAppointments);
        allAppointments = gson.fromJson(json, new TypeToken<List<Appointment>>() {
        }.getType());
        given(appointmentService.getAllAppointmentsByEmployeeId(1)).willReturn(allAppointments);
        mvc.perform(MockMvcRequestBuilders
                .get("/appointments/employee-id/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
    }

    @Test
    public void updateAppointmentAPI()
            throws Exception {
        Appointment appointment = new Appointment(1, new Date(), new Date(), new AppointmentType(), new ReasonType(), "reason", "attention", new Date());
        given(appointmentService.addAppointment(any(Appointment.class))).willReturn(appointment);
        mvc.perform(MockMvcRequestBuilders
                .put("/appointments/update", gson.toJson(appointment))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    public void deleteAppointmentAPI()
            throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/appointments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
