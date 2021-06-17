import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class MockSpyTest {
    @Mock
    List<String> mockList;

    @Spy
    List<String> spyList;

    @Test
    public void mockTest() {
        // add to fake arraylist object
        mockList.add(anyString());
        mockList.add(anyString());

        assertEquals(0, mockList.size());
    }

    @Test
    public void spyTest() {
        List<String> realList = new ArrayList<>();

        // create spy object to wrap COPY of real object
        // spy object calls REAL methods unless they are stubbed
        spyList = Mockito.spy(realList);

        // add to copied list object
        // -> real list not affected
        spyList.add(anyString());
        spyList.add(anyString());

        assertEquals(0, realList.size());
        assertEquals(2, spyList.size());
    }

}
