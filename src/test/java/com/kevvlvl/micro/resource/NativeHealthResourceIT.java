package com.kevvlvl.micro.resource;

import com.kevvlvl.micro.resource.HealthResourceTest;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeHealthResourceIT extends HealthResourceTest {

    // Execute the same tests but in native mode.
}