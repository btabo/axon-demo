package com.bta.axondemo.domain.plr.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class Profiles {

    private List<Profile> profiles = new ArrayList<>();

    public static class ProfilesBuilder {

        public ProfilesBuilder profile(Profile profile) {
            Profiles p = this.build();
            p.addProfile(profile);
            this.profiles = p.getProfiles();
            return this;
        }

        public ProfilesBuilder fromProfiles(Profiles profiles) {
            Optional.ofNullable(profiles).map(Profiles::getProfiles).ifPresent(p -> this.profiles = p);
            return this;
        }
    }

    private void addProfile(Profile profile) {
        this.profiles = Optional.ofNullable(this.profiles).orElse(new ArrayList<>());
        this.profiles.stream().filter(p -> p.getCustomerId().equals(profile.getCustomerId()))
                .findAny().ifPresent(p -> this.profiles.remove(p));
        this.profiles.add(profile);
    }


}
