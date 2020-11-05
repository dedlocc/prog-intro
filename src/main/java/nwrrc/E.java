package nwrrc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class E {
    public static void main(final String[] args) {
        final var in = new Scanner(System.in);
        final var n = in.nextInt();
        final var m = in.nextInt();

        final var cities = new City[n];

        for (int i = 0; i < n; ++i) {
            cities[i] = new City();
        }

        for (int i = 1; i < n; ++i) {
            final var v = in.nextInt();
            final var u = in.nextInt();
            cities[v - 1].addNeighbor(cities[u - 1]);
        }

        final var teams = new City[m];

        for (int i = 0; i < m; ++i) {
            final var c = in.nextInt();
            teams[i] = cities[c - 1];
        }

        System.out.println(teams[0].visit());
    }

    private static final class City {
        private int depth = -1;
        private final List<City> neighbors = new ArrayList<>();

        private int visit() {
            depth = 0;

            for (final var n : neighbors) {
                if (-1 == n.depth) {
                    final var d = n.visit();
                    if (depth < d) {
                        depth = d;
                    }
                }
            }

            return 1 + depth;
        }

        private void addNeighbor(final City city) {
            this.neighbors.add(city);
            city.neighbors.add(this);
        }

        private static void resetVisits(final City[] cities) {
            for (final var city : cities) {
                city.depth = -1;
            }
        }
    }
}
