package com.metacoding.springrocketdanv1.salaryRange;

public class SalaryRangeResponse {

    public static class SalaryRangeDTO {
        private int minSalary;
        private int maxSalary;

        public SalaryRangeDTO(int minSalary, int maxSalary) {
            this.minSalary = minSalary;
            this.maxSalary = maxSalary;
        }

        public int getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(int minSalary) {
            this.minSalary = minSalary;
        }

        public int getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(int maxSalary) {
            this.maxSalary = maxSalary;
        }
    }
}