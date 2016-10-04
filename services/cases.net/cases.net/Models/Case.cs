using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Org.Naima.Services.Cases.Models
{
    public class Case
    {
        public int Id { get; set; }
        [ForeignKey("Patient")]
        public int? PatientId { get; set; }
        public virtual Patient Patient { get; set; }

        [ForeignKey("Doctor")]
        public int? DoctorId { get; set; }
        public virtual Doctor Doctor { get; set; }

        [ForeignKey("CareProvider")]
        public int? CareProviderId { get; set; }
        public virtual CareProvider CareProvider { get; set; }

        public String Status { get; set; }
    }
}