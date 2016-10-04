using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Org.Naima.Services.Cases.Models
{
    public class CareProvider
    {
        public int Id { get; set; }
        public String Name { get; set; }

        [ForeignKey("Location")]
        public int? LocationId { get; set; }
        public virtual Location Location { get; set; }

        public ICollection<Case> Cases { get; set; }
    }
}