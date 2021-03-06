package com.mvpjava.electricity;

import com.mvpjava.electricity.Electricity;
import com.mvpjava.electricity.ElectricityProvider;
import com.mvpjava.electricity.Mass;
import com.mvpjava.exceptions.NoElectricityRuntimeException;
import java.util.Objects;

public class NuclearElectricityProviderImpl implements ElectricityProvider {

    private final Electricity electricity = new Electricity();
    private final Mass plutoniumLeft;
    private boolean energyOutput; // result of E=mc^2;

    public NuclearElectricityProviderImpl(Mass amountOfPlutoniumAvailable) {
        this.plutoniumLeft = Objects.requireNonNull(amountOfPlutoniumAvailable, "Cannot pass null Mass");
    }

    @Override
    public Electricity provideElectricity() throws NoElectricityRuntimeException {
        if (!isPlutoniumLeft()) {
            throw new NoElectricityRuntimeException("No more plutonium to produce energy");
        }
        return getElectricity();
    }

    @Override
    public String getPowerSourceDetails() {
        return "Electricity generated by Nuclean Fission with Plutonium-238";
    }

    //E=mc^2
    private double calculateEnergyOutput() {
        final double SPEED_OF_LIGHT = 3.0e8;
        return (getPlutoniumLeft().getWeight() * (Math.pow(SPEED_OF_LIGHT, 2)));
    }

    private Mass getPlutoniumLeft() {
        return plutoniumLeft;
    }

    private boolean isPlutoniumLeft() {
        //could implenet a half life formula to make it more interesting!
        this.energyOutput = (calculateEnergyOutput() > 0);
        return energyOutput;
    }

    public Electricity getElectricity() {
        return electricity;
    }

}
